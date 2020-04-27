package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.Cellar;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Cellar}.
 */
public interface CellarService {

    /**
     * Save a cellar.
     *
     * @param cellar the entity to save.
     * @return the persisted entity.
     */
    Cellar save(Cellar cellar);

    /**
     * Get all the cellars.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<Cellar> findAll(Pageable pageable);

    /**
     * Get the "id" cellar.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<Cellar> findOne(Long id);

    /**
     * Delete the "id" cellar.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
