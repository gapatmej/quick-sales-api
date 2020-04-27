package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.TaxInvoice;

import java.util.List;
import java.util.Optional;

/**
 * Service Interface for managing {@link TaxInvoice}.
 */
public interface TaxInvoiceService {

    /**
     * Save a taxInvoice.
     *
     * @param taxInvoice the entity to save.
     * @return the persisted entity.
     */
    TaxInvoice save(TaxInvoice taxInvoice);

    /**
     * Get all the taxInvoices.
     *
     * @return the list of entities.
     */
    List<TaxInvoice> findAll();

    /**
     * Get the "id" taxInvoice.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<TaxInvoice> findOne(Long id);

    /**
     * Delete the "id" taxInvoice.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
