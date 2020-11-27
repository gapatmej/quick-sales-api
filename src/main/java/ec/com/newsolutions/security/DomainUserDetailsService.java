package ec.com.newsolutions.security;

import ec.com.newsolutions.domain.User;
import ec.com.newsolutions.repository.UserRepository;
import ec.com.newsolutions.service.dto.WorkspaceDTO;
import org.hibernate.validator.internal.constraintvalidators.hv.EmailValidator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Authenticate a user from the database.
 */
@Component("userDetailsService")
public class DomainUserDetailsService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(DomainUserDetailsService.class);

    private final UserRepository userRepository;

    public DomainUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetailsCustom loadUserByUsername(final String email) {
        log.debug("Authenticating {}", email);

        return userRepository.findOneWithAuthoritiesByEmailIgnoreCase(email)
            .map(user -> createSpringSecurityUser(email, user))
            .orElseThrow(() -> new UsernameNotFoundException("User with email " + email + " was not found in the database"));

    }

    private UserDetailsCustom createSpringSecurityUser(String lowercaseLogin, User user) {

        if (!user.isActivated()) {
            throw new UserNotActivatedException("User " + lowercaseLogin + " was not activated");
        }
        List<GrantedAuthority> grantedAuthorities = user.getAuthorities().stream()
            .map(authority -> new SimpleGrantedAuthority(authority.getName()))
            .collect(Collectors.toList());
        return new UserDetailsCustom(user.getEmail(),
            user.getPassword(),
            grantedAuthorities, new WorkspaceDTO());
    }
}
