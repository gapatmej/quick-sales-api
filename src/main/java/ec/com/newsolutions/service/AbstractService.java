package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.AbstractMainEntity;
import ec.com.newsolutions.domain.DetailInvoiceClient;

import java.util.Optional;

public interface AbstractService<E extends AbstractMainEntity> {

    E save(E e);
    Optional<E> findOne(Long id);
}
