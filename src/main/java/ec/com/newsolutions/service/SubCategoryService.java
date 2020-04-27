package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.SubCategory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link SubCategory}.
 */
public interface SubCategoryService {

    /**
     * Save a subCategory.
     *
     * @param subCategory the entity to save.
     * @return the persisted entity.
     */
    SubCategory save(SubCategory subCategory);

    /**
     * Get all the subCategories.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<SubCategory> findAll(Pageable pageable);

    /**
     * Get the "id" subCategory.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<SubCategory> findOne(Long id);

    /**
     * Delete the "id" subCategory.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
