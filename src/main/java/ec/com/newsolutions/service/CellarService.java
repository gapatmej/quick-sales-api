package ec.com.newsolutions.service;

import ec.com.newsolutions.service.dto.CellarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;


public interface CellarService {

    CellarDTO save(CellarDTO cellar);

    Page<CellarDTO> findAll(String search, Pageable pageable);

    Optional<CellarDTO> findOne(Long id);

    void delete(Long id);
}
