package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.DetailInvoice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link DetailInvoice}.
 */
public interface DetailInvoiceService {

    /**
     * Save a detailInvoice.
     *
     * @param detailInvoice the entity to save.
     * @return the persisted entity.
     */
    DetailInvoice save(DetailInvoice detailInvoice);

    /**
     * Get all the detailInvoices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<DetailInvoice> findAll(Pageable pageable);

    /**
     * Get the "id" detailInvoice.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<DetailInvoice> findOne(Long id);

    /**
     * Delete the "id" detailInvoice.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
