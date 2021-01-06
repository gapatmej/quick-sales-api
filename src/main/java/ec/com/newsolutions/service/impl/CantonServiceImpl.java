package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.Canton;
import ec.com.newsolutions.repository.CantonRepository;
import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.service.CantonService;
import ec.com.newsolutions.service.dto.CantonDTO;
import ec.com.newsolutions.service.mapper.CantonMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
public class CantonServiceImpl extends AbstractService implements CantonService {

    private final CantonMapper cantonMapper;
    private final CantonRepository cantonRepository;

    public CantonServiceImpl(CantonMapper cantonMapper, CantonRepository cantonRepository) {
        super(CantonServiceImpl.class);
        this.cantonMapper = cantonMapper;
        this.cantonRepository = cantonRepository;
    }

    @Override
    public CantonDTO save(CantonDTO invoiceClientDTO) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CantonDTO> findAll(String search, Pageable pageable) {
        log.debug("Request to get all Cantons");
        return cantonRepository.findAll( UtilsSpecification.<Canton>getSpecificationWithWorkspace(search), pageable).map(cantonMapper::toDto);
    }

    @Override
    public Optional<CantonDTO> findOneDto(Long id) {
        return findOne(id).map(cantonMapper::toDto);
    }

    @Override
    public Canton save(Canton canton) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<Canton> findOne(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }
}
