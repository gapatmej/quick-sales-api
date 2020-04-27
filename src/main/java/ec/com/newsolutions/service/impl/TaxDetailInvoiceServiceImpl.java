package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.service.TaxDetailInvoiceService;
import ec.com.newsolutions.domain.TaxDetailInvoice;
import ec.com.newsolutions.repository.TaxDetailInvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link TaxDetailInvoice}.
 */
@Service
@Transactional
public class TaxDetailInvoiceServiceImpl implements TaxDetailInvoiceService {

    private final Logger log = LoggerFactory.getLogger(TaxDetailInvoiceServiceImpl.class);

    private final TaxDetailInvoiceRepository taxDetailInvoiceRepository;

    public TaxDetailInvoiceServiceImpl(TaxDetailInvoiceRepository taxDetailInvoiceRepository) {
        this.taxDetailInvoiceRepository = taxDetailInvoiceRepository;
    }

    /**
     * Save a taxDetailInvoice.
     *
     * @param taxDetailInvoice the entity to save.
     * @return the persisted entity.
     */
    @Override
    public TaxDetailInvoice save(TaxDetailInvoice taxDetailInvoice) {
        log.debug("Request to save TaxDetailInvoice : {}", taxDetailInvoice);
        return taxDetailInvoiceRepository.save(taxDetailInvoice);
    }

    /**
     * Get all the taxDetailInvoices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<TaxDetailInvoice> findAll(Pageable pageable) {
        log.debug("Request to get all TaxDetailInvoices");
        return taxDetailInvoiceRepository.findAll(pageable);
    }

    /**
     * Get one taxDetailInvoice by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<TaxDetailInvoice> findOne(Long id) {
        log.debug("Request to get TaxDetailInvoice : {}", id);
        return taxDetailInvoiceRepository.findById(id);
    }

    /**
     * Delete the taxDetailInvoice by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete TaxDetailInvoice : {}", id);
        taxDetailInvoiceRepository.deleteById(id);
    }
}
