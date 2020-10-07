package ec.com.newsolutions.service;

import ec.com.newsolutions.service.dto.UnitDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface UnitService {

    UnitDTO save(UnitDTO unitDTO);

    Page<UnitDTO> findAll(String search, Pageable pageable);

    Optional<UnitDTO> findOne(Long id);

    void delete(Long id);
}
