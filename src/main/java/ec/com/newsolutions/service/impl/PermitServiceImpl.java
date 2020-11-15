package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.Permit;
import ec.com.newsolutions.repository.PermitRepository;
import ec.com.newsolutions.service.PermitService;
import ec.com.newsolutions.service.dto.PermitDTO;
import ec.com.newsolutions.service.mapper.PermitMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class PermitServiceImpl extends AbstractService  implements PermitService {

    private final PermitRepository permitRepository;

    private final PermitMapper permitMapper;

    public PermitServiceImpl(PermitRepository permitRepository, PermitMapper permitMapper) {
        super(PermitServiceImpl.class);
        this.permitRepository = permitRepository;
        this.permitMapper = permitMapper;
    }
    @Override
    public Page<PermitDTO> findAll(String search, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<PermitDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public List<PermitDTO> findAllByAuthoritiesIn(List<Long> authorityIds) {
        List<Permit> permits = permitRepository.findAllByAuthoritiesIn(authorityIds);
        return permitMapper.toDto(permits);
    }

}
