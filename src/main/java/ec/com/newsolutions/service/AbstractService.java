package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.AbstractMainEntity;
import ec.com.newsolutions.service.dto.AbstractMainDTO;
import org.dom4j.tree.AbstractEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AbstractService<D extends AbstractMainDTO,E extends AbstractMainEntity> {

    D save(D entityDTO);

    Page<D> findAll(String search, Pageable pageable);

    Optional<D> findOneDto(Long id);

    Optional<E> findOne(Long id);

    void delete(Long id);
}
