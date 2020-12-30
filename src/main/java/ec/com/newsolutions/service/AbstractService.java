package ec.com.newsolutions.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AbstractService<D,E> {

    D save(D invoiceClientDTO);

    Page<D> findAll(String search, Pageable pageable);

    Optional<D> findOneDto(Long id);

    Optional<E> findOne(Long id);

    void delete(Long id);
}
