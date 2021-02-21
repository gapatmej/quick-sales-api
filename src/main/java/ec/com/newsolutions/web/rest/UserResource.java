package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.domain.User;
import ec.com.newsolutions.repository.UserRepository;
import ec.com.newsolutions.service.UserService;
import ec.com.newsolutions.service.dto.UserDTO;
import ec.com.newsolutions.utils.GsonUtils;
import ec.com.newsolutions.web.rest.errors.EmailAlreadyUsedException;
import ec.com.newsolutions.web.rest.errors.IdExistException;

import ec.com.newsolutions.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api")
public class UserResource extends AbstractResource {

    private final UserService userService;

    private final UserRepository userRepository;

    public UserResource(UserService userService, UserRepository userRepository) {
        super(UserResource.class, "user" );
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @PostMapping("/users")
    public ResponseEntity<UserDTO> createUser(@Valid @RequestBody UserDTO userDTO) throws URISyntaxException {
        log.debug("REST request to save User : {}", GsonUtils.entityToJson(userDTO));
        if (userDTO.getId() != null) throw new IdExistException(entityName);

        if (userRepository.findOneByEmailIgnoreCase(userDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyUsedException();
        } else {
            UserDTO newUser = userService.create(userDTO);
            return ResponseEntity.created(new URI("/api/users/" + newUser.getId()))
                .headers(HeaderUtil.createEntityCreationAlert(true,entityName, newUser.getId().toString()))
                .body(newUser);
        }
    }

    @PutMapping("/users")
    public ResponseEntity<UserDTO> updateUser(@Valid @RequestBody UserDTO userDTO) {
        log.debug("REST request to update User : {}", GsonUtils.entityToJson(userDTO));
        Optional<User> existingUser = userRepository.findOneByEmailIgnoreCase(userDTO.getEmail());
        if (existingUser.isPresent() && (!existingUser.get().getId().equals(userDTO.getId()))) {
            throw new EmailAlreadyUsedException();
        }
        UserDTO result = userService.update(userDTO);

        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(true, entityName, result.getId().toString()))
            .body(result);
    }

    @GetMapping("/users")
    public ResponseEntity<List<UserDTO>> getAllUsers(String search, Pageable pageable) {
        log.debug("REST request to get a page of Organizations");
        final Page<UserDTO> page = userService.findAll(search, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        log.debug("REST request to get User : {}", id);
        Optional<UserDTO> userDTO = userService.findOne(id);
        return ResponseUtil.wrapOrNotFound(userDTO);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        log.debug("REST request to delete User: {}", id);
        userService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(true, entityName, id.toString())).build();
    }
}
