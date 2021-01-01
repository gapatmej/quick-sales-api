package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.AbstractMainEntity;

import java.util.Optional;

public interface AbstractService<E extends AbstractMainEntity> {

    Optional<E> findOne(Long id);
}
