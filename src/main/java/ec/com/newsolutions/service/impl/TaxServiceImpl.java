package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.service.TaxService;
import ec.com.newsolutions.domain.Tax;
import ec.com.newsolutions.repository.TaxRepository;
import ec.com.newsolutions.service.dto.TaxDTO;
import ec.com.newsolutions.service.mapper.TaxMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Tax}.
 */
@Service
@Transactional
public class TaxServiceImpl implements TaxService {

    private final Logger log = LoggerFactory.getLogger(UnitServiceImpl.class);

    private final TaxRepository taxRepository;
    private final TaxMapper taxMapper;

    public TaxServiceImpl(TaxRepository taxRepository, TaxMapper taxMapper) {
        this.taxRepository = taxRepository;
        this.taxMapper = taxMapper;
    }

    @Override
    public TaxDTO save(TaxDTO taxDTO) {
        log.debug("Request to save Unit : {}", taxDTO);
        Tax tax =  taxRepository.save(taxMapper.toEntity(taxDTO));
        return taxMapper.toDto(tax);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaxDTO> findAll(String search, Pageable pageable) {
        log.debug("Request to get all Units");
        return taxRepository.findAll(UtilsSpecification.<Tax>getSpecification(search), pageable).map(taxMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaxDTO> findOne(Long id) {
        log.debug("Request to get Unit : {}", id);
        return taxRepository.findById(id).map(taxMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Unit : {}", id);
        taxRepository.deleteById(id);
    }
}
