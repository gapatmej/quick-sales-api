package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.service.CellarService;
import ec.com.newsolutions.domain.Cellar;
import ec.com.newsolutions.repository.CellarRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Cellar}.
 */
@Service
@Transactional
public class CellarServiceImpl implements CellarService {

    private final Logger log = LoggerFactory.getLogger(CellarServiceImpl.class);

    private final CellarRepository cellarRepository;

    public CellarServiceImpl(CellarRepository cellarRepository) {
        this.cellarRepository = cellarRepository;
    }

    /**
     * Save a cellar.
     *
     * @param cellar the entity to save.
     * @return the persisted entity.
     */
    @Override
    public Cellar save(Cellar cellar) {
        log.debug("Request to save Cellar : {}", cellar);
        return cellarRepository.save(cellar);
    }

    /**
     * Get all the cellars.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<Cellar> findAll(Pageable pageable) {
        log.debug("Request to get all Cellars");
        return cellarRepository.findAll(pageable);
    }

    /**
     * Get one cellar by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<Cellar> findOne(Long id) {
        log.debug("Request to get Cellar : {}", id);
        return cellarRepository.findById(id);
    }

    /**
     * Delete the cellar by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cellar : {}", id);
        cellarRepository.deleteById(id);
    }
}
