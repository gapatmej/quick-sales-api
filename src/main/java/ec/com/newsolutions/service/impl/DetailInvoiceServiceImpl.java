package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.service.DetailInvoiceService;
import ec.com.newsolutions.domain.DetailInvoice;
import ec.com.newsolutions.repository.DetailInvoiceRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link DetailInvoice}.
 */
@Service
@Transactional
public class DetailInvoiceServiceImpl implements DetailInvoiceService {

    private final Logger log = LoggerFactory.getLogger(DetailInvoiceServiceImpl.class);

    private final DetailInvoiceRepository detailInvoiceRepository;

    public DetailInvoiceServiceImpl(DetailInvoiceRepository detailInvoiceRepository) {
        this.detailInvoiceRepository = detailInvoiceRepository;
    }

    /**
     * Save a detailInvoice.
     *
     * @param detailInvoice the entity to save.
     * @return the persisted entity.
     */
    @Override
    public DetailInvoice save(DetailInvoice detailInvoice) {
        log.debug("Request to save DetailInvoice : {}", detailInvoice);
        return detailInvoiceRepository.save(detailInvoice);
    }

    /**
     * Get all the detailInvoices.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<DetailInvoice> findAll(Pageable pageable) {
        log.debug("Request to get all DetailInvoices");
        return detailInvoiceRepository.findAll(pageable);
    }

    /**
     * Get one detailInvoice by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<DetailInvoice> findOne(Long id) {
        log.debug("Request to get DetailInvoice : {}", id);
        return detailInvoiceRepository.findById(id);
    }

    /**
     * Delete the detailInvoice by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete DetailInvoice : {}", id);
        detailInvoiceRepository.deleteById(id);
    }
}
