package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.service.TaxInvoiceService;
import ec.com.newsolutions.domain.TaxInvoice;
import ec.com.newsolutions.repository.TaxInvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * Service Implementation for managing {@link TaxInvoice}.
 */
@Service
@Transactional
public class TaxInvoiceServiceImpl implements TaxInvoiceService {

    private final Logger log = LoggerFactory.getLogger(TaxInvoiceServiceImpl.class);

    private final TaxInvoiceRepository taxInvoiceRepository;

    public TaxInvoiceServiceImpl(TaxInvoiceRepository taxInvoiceRepository) {
        this.taxInvoiceRepository = taxInvoiceRepository;
    }

    /**
     * Save a taxInvoice.
     *
     * @param taxInvoice the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TaxInvoice save(TaxInvoice taxInvoice) {
        log.debug("Request to save TaxInvoice : {}", taxInvoice);
        return taxInvoiceRepository.save(taxInvoice);
    }

    /**
     * Get all the taxInvoices.
     *
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public List<TaxInvoice> findAll() {
        log.debug("Request to get all TaxInvoices");
        return taxInvoiceRepository.findAll();
    }

    /**
     * Get one taxInvoice by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TaxInvoice> findOne(Long id) {
        log.debug("Request to get TaxInvoice : {}", id);
        return taxInvoiceRepository.findById(id);
    }

    /**
     * Delete the taxInvoice by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaxInvoice : {}", id);
        taxInvoiceRepository.deleteById(id);
    }
}
