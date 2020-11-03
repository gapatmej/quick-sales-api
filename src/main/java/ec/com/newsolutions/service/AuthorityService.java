package ec.com.newsolutions.service;

import ec.com.newsolutions.service.dto.AuthorityDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AuthorityService {

    AuthorityDTO save(AuthorityDTO authorityDTO);

    Page<AuthorityDTO> findAll(String search, Pageable pageable);

    Optional<AuthorityDTO> findOne(Long id);

    void delete(Long id);
}
