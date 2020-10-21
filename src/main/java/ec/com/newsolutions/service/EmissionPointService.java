package ec.com.newsolutions.service;

import ec.com.newsolutions.service.dto.EmissionPointDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface EmissionPointService {

    EmissionPointDTO save(EmissionPointDTO emissionPointDTO);

    Page<EmissionPointDTO> findAll(String search, Pageable pageable);

    Optional<EmissionPointDTO> findOne(Long id);

    void delete(Long id);
}
