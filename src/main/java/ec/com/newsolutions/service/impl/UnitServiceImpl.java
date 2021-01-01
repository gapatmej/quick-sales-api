package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.Unit;
import ec.com.newsolutions.repository.UnitRepository;
import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.service.InvoiceClientService;
import ec.com.newsolutions.service.UnitService;
import ec.com.newsolutions.service.dto.UnitDTO;
import ec.com.newsolutions.service.mapper.UnitMapper;

import ec.com.newsolutions.utils.GsonUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
public class UnitServiceImpl extends AbstractService implements UnitService {

    private final UnitRepository unitRepository;
    private final UnitMapper unitMapper;

    public UnitServiceImpl(UnitRepository unitRepository, UnitMapper unitMapper) {
        super(UnitServiceImpl.class);
        this.unitRepository = unitRepository;
        this.unitMapper = unitMapper;
    }

    @Override
    public UnitDTO save(UnitDTO unitDTO) {
        log.debug("Request to save Unit : {}", GsonUtils.entityToJson(unitDTO));
        Unit unit =  unitRepository.save(unitMapper.toEntity(unitDTO));
        return unitMapper.toDto(unit);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<UnitDTO> findAll(String search, Pageable pageable) {
        return unitRepository.findAll( UtilsSpecification.<Unit>getSpecificationWithWorkspace(search), pageable).map(unitMapper::toDto);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UnitDTO> findOneDto(Long id) {
        return findOne(id).map(unitMapper::toDto);
    }

    @Override
    public Optional<Unit> findOne(Long id) {
        log.debug("Request to get Unit : {}", id);
        return unitRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Unit : {}", id);
        unitRepository.deleteById(id);
    }

}
