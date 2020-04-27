package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.PayWay;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link PayWay}.
 */
public interface PayWayService {

    /**
     * Save a payWay.
     *
     * @param payWay the entity to save.
     * @return the persisted entity.
     */
    PayWay save(PayWay payWay);

    /**
     * Get all the payWays.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<PayWay> findAll(Pageable pageable);

    /**
     * Get the "id" payWay.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<PayWay> findOne(Long id);

    /**
     * Delete the "id" payWay.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
