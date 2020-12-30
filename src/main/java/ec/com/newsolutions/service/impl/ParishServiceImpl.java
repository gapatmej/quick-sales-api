package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.Parish;
import ec.com.newsolutions.repository.ParishRepository;
import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.service.ParishService;
import ec.com.newsolutions.service.dto.ParishDTO;
import ec.com.newsolutions.service.mapper.ParishMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ParishServiceImpl extends AbstractService implements ParishService {

    private final ParishMapper parishMapper;
    private final ParishRepository parishRepository;

    public ParishServiceImpl(ParishMapper parishMapper, ParishRepository parishRepository) {
        super(ParishServiceImpl.class);
        this.parishMapper = parishMapper;
        this.parishRepository = parishRepository;
    }

    @Override
    public ParishDTO save(ParishDTO invoiceClientDTO) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ParishDTO> findAll(String search, Pageable pageable) {
        log.debug("Request to get all Parishes");
        return parishRepository.findAll( UtilsSpecification.<Parish>getSpecificationWithWorkspace(search), pageable).map(parishMapper::toDto);
    }

    @Override
    public Optional<ParishDTO> findOneDto(Long id) {
        return findOne(id).map(parishMapper::toDto);
    }

    @Override
    public Optional<Parish> findOne(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }
}
