package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.InvoiceClient;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link InvoiceClient}.
 */
public interface InvoiceClientService {

    /**
     * Save a invoiceClient.
     *
     * @param invoiceClient the entity to save.
     * @return the persisted entity.
     */
    InvoiceClient save(InvoiceClient invoiceClient);

    /**
     * Get all the invoiceClients.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    Page<InvoiceClient> findAll(Pageable pageable);

    /**
     * Get the "id" invoiceClient.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    Optional<InvoiceClient> findOne(Long id);

    /**
     * Delete the "id" invoiceClient.
     *
     * @param id the id of the entity.
     */
    void delete(Long id);
}
