package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.service.CellarService;
import ec.com.newsolutions.domain.Cellar;
import ec.com.newsolutions.repository.CellarRepository;
import ec.com.newsolutions.service.dto.CellarDTO;
import ec.com.newsolutions.service.mapper.CellarMapper;
import ec.com.newsolutions.utils.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class CellarServiceImpl extends AbstractService implements CellarService {

    private final CellarMapper cellarMapper;
    private final CellarRepository cellarRepository;

    public CellarServiceImpl(CellarMapper cellarMapper, CellarRepository cellarRepository) {
        super(CellarServiceImpl.class);
        this.cellarMapper = cellarMapper;
        this.cellarRepository = cellarRepository;
    }

    @Override
    public CellarDTO save(CellarDTO cellarDTO) {
        log.debug("Request to save Cellar : {}", GsonUtils.entityToJson(cellarDTO));
        Cellar cellar =  cellarRepository.save(cellarMapper.toEntity(cellarDTO));
        return cellarMapper.toDto(cellar);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CellarDTO> findAll(String search, Pageable pageable) {
        log.debug("Request to get all Cellars");
        return cellarRepository.findAll( UtilsSpecification.<Cellar>getSpecificationWithWorkspace(search), pageable).map(cellarMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CellarDTO> findOneDto(Long id) {
        return findOne(id).map(cellarMapper::toDto);
    }

    @Override
    public Optional<Cellar> findOne(Long id) {
        log.debug("Request to get Cellar : {}", id);
        return cellarRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Cellar : {}", id);
        cellarRepository.deleteById(id);
    }
}
