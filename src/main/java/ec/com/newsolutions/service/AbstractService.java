package ec.com.newsolutions.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AbstractService<T> {

    T save(T invoiceClientDTO);

    Page<T> findAll(String search, Pageable pageable);

    Optional<T> findOne(Long id);

    void delete(Long id);
}
