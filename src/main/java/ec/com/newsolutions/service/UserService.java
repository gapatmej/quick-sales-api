package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.Authority;
import ec.com.newsolutions.domain.User;
import ec.com.newsolutions.repository.AuthorityRepository;
import ec.com.newsolutions.repository.UserRepository;
import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.security.SecurityUtils;
import ec.com.newsolutions.service.dto.UserDTO;

import ec.com.newsolutions.service.impl.AbstractService;
import ec.com.newsolutions.service.mapper.UserMapper;
import ec.com.newsolutions.web.rest.errors.EntityNotFoundException;
import io.github.jhipster.security.RandomUtil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService extends AbstractService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private final AuthorityRepository authorityRepository;

    private final UserMapper userMapper;

    private final MailService mailService;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder, AuthorityRepository authorityRepository, UserMapper userMapper, MailService mailService) {
        super(UserService.class);
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authorityRepository = authorityRepository;
        this.userMapper = userMapper;
        this.mailService = mailService;
    }

    public Optional<User> activateRegistration(String key) {
        log.debug("Activating user for activation key {}", key);
        return userRepository.findOneByActivationKey(key)
            .map(user -> {
                // activate given user for the registration key.
                user.setActivated(true);
                user.setActivationKey(null);
                log.debug("Activated user: {}", user);
                return user;
            });
    }

    public Optional<User> completePasswordReset(String newPassword, String key) {
        log.debug("Reset user password for reset key {}", key);
        return userRepository.findOneByResetKey(key)
            .filter(user -> user.getResetDate().isAfter(Instant.now().minusSeconds(86400)))
            .map(user -> {
                user.setPassword(passwordEncoder.encode(newPassword));
                user.setResetKey(null);
                user.setResetDate(null);
                return user;
            });
    }

    public Optional<User> requestPasswordReset(String mail) {
        return userRepository.findOneByEmailIgnoreCase(mail)
            .filter(User::isActivated)
            .map(user -> {
                user.setResetKey(RandomUtil.generateResetKey());
                user.setResetDate(Instant.now());
                return user;
            });
    }

    public User registerUser(UserDTO userDTO, String password) {
        userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).ifPresent(existingUser -> {
            boolean removed = removeNonActivatedUser(existingUser);
            if (!removed) {
                throw new EmailAlreadyUsedException();
            }
        });
        User newUser = new User();
        String encryptedPassword = passwordEncoder.encode(password);
        // new user gets initially a generated password
        newUser.setPassword(encryptedPassword);
        newUser.setFirstName(userDTO.getFirstName());
        newUser.setLastName(userDTO.getLastName());
        if (userDTO.getEmail() != null) {
            newUser.setEmail(userDTO.getEmail().toLowerCase());
        }
        newUser.setImageUrl(userDTO.getImageUrl());
        newUser.setLangKey(userDTO.getLangKey());
        // new user is not active
        newUser.setActivated(false);
        // new user gets registration key
        newUser.setActivationKey(RandomUtil.generateActivationKey());
        Set<Authority> authorities = new HashSet<>();
       // authorityRepository.findById(AuthoritiesConstants.USER).ifPresent(authorities::add);
        newUser.setAuthorities(authorities);
        userRepository.save(newUser);
        log.debug("Created Information for User: {}", newUser);
        return newUser;
    }

    private boolean removeNonActivatedUser(User existingUser) {
        if (existingUser.isActivated()) {
             return false;
        }
        userRepository.delete(existingUser);
        userRepository.flush();
        return true;
    }

    public UserDTO create(UserDTO userDTO) {

        User user = userMapper.userDTOToUser(userDTO);
        String encryptedPassword = passwordEncoder.encode(RandomUtil.generatePassword());
        user.setPassword(encryptedPassword);
        user.setResetKey(RandomUtil.generateResetKey());
        user.setResetDate(Instant.now());
        user.setActivated(true);
        User result = userRepository.save(user);
        log.debug("Created Information for User: {}", result);
        mailService.sendCreationEmail(result);
        return userMapper.userToUserDTO(result);
    }

    public UserDTO update(UserDTO userDTO) {

        User user = userRepository.findById(userDTO.getId()).orElseThrow(()->new EntityNotFoundException(userDTO.getId()));
        User userUpdate = userMapper.userDTOToUser(userDTO);

        user.setIdentification(userUpdate.getIdentification());
        user.setEmail(userUpdate.getEmail().toLowerCase());
        user.setFirstName(userUpdate.getFirstName());
        user.setLastName(userUpdate.getLastName());
        user.setPhone(userUpdate.getPhone());
        user.setActivated(userUpdate.isActivated());
        user.setLangKey(userUpdate.getLangKey());
        user.setImageUrl(userUpdate.getImageUrl());
        user.setAuthorities(userUpdate.getAuthorities());
        user.setOrganizations(userUpdate.getOrganizations());
        user.setBranchOffices(userUpdate.getBranchOffices());

        user = userRepository.save(user);
        log.debug("Created Information for User: {}", user);
        return userMapper.userToUserDTO(user);
    }

    public void changePassword(String currentClearTextPassword, String newPassword) {
        SecurityUtils.getCurrentUserEmail()
            .flatMap(userRepository::findOneByEmailIgnoreCase)
            .ifPresent(user -> {
                String currentEncryptedPassword = user.getPassword();
                if (!passwordEncoder.matches(currentClearTextPassword, currentEncryptedPassword)) {
                    throw new InvalidPasswordException();
                }
                String encryptedPassword = passwordEncoder.encode(newPassword);
                user.setPassword(encryptedPassword);
                log.debug("Changed password for User: {}", user);
            });
    }

   /* @Transactional(readOnly = true)
    public Page<UserDTO> getAllManagedUsers(Pageable pageable) {
       // return userRepository.findAllByLoginNot(pageable, Constants.ANONYMOUS_USER).map(UserDTO::new);
    }*/

  /*  @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthoritiesByLogin(String email) {
        return userRepository.findOneWithLazyEntityByEmailIgnoreCase(email);
    }*/

    @Transactional(readOnly = true)
    public Optional<User> getUserWithAuthorities(Long id) {
        return userRepository.findOneWithAuthoritiesById(id);
    }

    @Transactional(readOnly = true)
    public Optional<User> getUserWithLazy() {
        return SecurityUtils.getCurrentUserEmail().flatMap(userRepository::findOneWithLazyEntitiesByEmailIgnoreCase);
    }

    @Scheduled(cron = "0 0 1 * * ?")
    public void removeNotActivatedUsers() {
        userRepository
            .findAllByActivatedIsFalseAndActivationKeyIsNotNullAndCreatedDateBefore(Instant.now().minus(3, ChronoUnit.DAYS))
            .forEach(user -> {
                userRepository.delete(user);
            });
    }

    public List<String> getAuthorities() {
        return authorityRepository.findAll().stream().map(Authority::getName).collect(Collectors.toList());
    }

    public Page<UserDTO> findAll(String search, Pageable pageable) {
        log.debug("Request to get all Users");
        return userRepository.findAll( UtilsSpecification.<User>getSpecification(search), pageable).map(userMapper::userToUserDTOLight);
    }

    public Optional<UserDTO> findOne(Long id) {
        log.debug("Request to get User : {}", id);
        return userRepository.findById(id).map(userMapper::userToUserDTO);
    }

    public void delete(Long id) {
        log.debug("Request to delete Organization : {}", id);
        userRepository.deleteById(id);
    }

}
