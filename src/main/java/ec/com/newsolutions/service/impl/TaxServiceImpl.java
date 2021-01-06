package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.service.TaxService;
import ec.com.newsolutions.domain.Tax;
import ec.com.newsolutions.repository.TaxRepository;
import ec.com.newsolutions.service.dto.TaxDTO;
import ec.com.newsolutions.service.mapper.TaxMapper;
import ec.com.newsolutions.utils.GsonUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class TaxServiceImpl extends AbstractService implements TaxService {

    private final TaxRepository taxRepository;
    private final TaxMapper taxMapper;

    public TaxServiceImpl(TaxRepository taxRepository, TaxMapper taxMapper) {
        super(TaxServiceImpl.class);
        this.taxRepository = taxRepository;
        this.taxMapper = taxMapper;
    }

    @Override
    public TaxDTO save(TaxDTO taxDTO) {
        log.debug("Request to save Unit : {}", GsonUtils.entityToJson(taxDTO));
        Tax tax =  save(taxMapper.toEntity(taxDTO));
        return taxMapper.toDto(tax);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<TaxDTO> findAll(String search, Pageable pageable) {
        log.debug("Request to get all Units");
        return taxRepository.findAll(UtilsSpecification.<Tax>getSpecificationWithWorkspace(search), pageable).map(taxMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TaxDTO> findOneDto(Long id) {
        return findOne(id).map(taxMapper::toDto);
    }

    @Override
    public Tax save(Tax tax) {
        Tax result =  taxRepository.save(tax);
        return result;
    }

    @Override
    public Optional<Tax> findOne(Long id) {
        log.debug("Request to get Unit : {}", id);
        return taxRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Unit : {}", id);
        taxRepository.deleteById(id);
    }

    @Override
    public List<Tax> findByIdIn(List<Long> ids) {
        return taxRepository.findByIdIn(ids);
    }
}
