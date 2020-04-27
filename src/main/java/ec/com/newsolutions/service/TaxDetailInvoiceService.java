package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.TaxDetailInvoice;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link TaxDetailInvoice}.
 */
public interface TaxDetailInvoiceService {

    /**
     * Save a taxDetailInvoice.
     *
     * @param taxDetailInvoice the entity to save.
     * @return the persisted entity.
     */
    TaxDetailInvoice save(TaxDetailInvoice taxDetailInvoice);

    /**
     * Get all the taxDetailInvoices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<TaxDetailInvoice> findAll(Pageable pageable);

    /**
     * Get the "id" taxDetailInvoice.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaxDetailInvoice> findOne(Long id);

    /**
     * Delete the "id" taxDetailInvoice.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
