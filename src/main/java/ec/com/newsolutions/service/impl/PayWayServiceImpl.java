package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.service.PayWayService;
import ec.com.newsolutions.domain.PayWay;
import ec.com.newsolutions.repository.PayWayRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link PayWay}.
 */
@Service
@Transactional
public class PayWayServiceImpl implements PayWayService {

    private final Logger log = LoggerFactory.getLogger(PayWayServiceImpl.class);

    private final PayWayRepository payWayRepository;

    public PayWayServiceImpl(PayWayRepository payWayRepository) {
        this.payWayRepository = payWayRepository;
    }

    /**
     * Save a payWay.
     *
     * @param payWay the entity to save.
     * @return the persisted entity.
     */
    @Override
    public PayWay save(PayWay payWay) {
        log.debug("Request to save PayWay : {}", payWay);
        return payWayRepository.save(payWay);
    }

    /**
     * Get all the payWays.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<PayWay> findAll(Pageable pageable) {
        log.debug("Request to get all PayWays");
        return payWayRepository.findAll(pageable);
    }

    /**
     * Get one payWay by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<PayWay> findOne(Long id) {
        log.debug("Request to get PayWay : {}", id);
        return payWayRepository.findById(id);
    }

    /**
     * Delete the payWay by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete PayWay : {}", id);
        payWayRepository.deleteById(id);
    }
}
