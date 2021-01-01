package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.AbstractMainEntity;
import ec.com.newsolutions.service.dto.AbstractMainDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AbstractServiceRest<D extends AbstractMainDTO> {

    D save(D entityDTO);

    Page<D> findAll(String search, Pageable pageable);

    Optional<D> findOneDto(Long id);

    void delete(Long id);
}
