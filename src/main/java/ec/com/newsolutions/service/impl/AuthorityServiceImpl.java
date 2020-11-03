package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.Authority;
import ec.com.newsolutions.repository.AuthorityRepository;
import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.service.AuthorityService;
import ec.com.newsolutions.service.dto.AuthorityDTO;
import ec.com.newsolutions.service.mapper.AuthorityMapper;
import ec.com.newsolutions.utils.GsonUtils;
import io.micrometer.core.instrument.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class AuthorityServiceImpl extends AbstractService implements AuthorityService{
    private final AuthorityMapper authorityMapper;
    private final AuthorityRepository authorityRepository;

    public AuthorityServiceImpl(AuthorityMapper authorityMapper, AuthorityRepository authorityRepository) {
        super(AuthorityServiceImpl.class);
        this.authorityMapper = authorityMapper;
        this.authorityRepository = authorityRepository;
    }

    @Override
    public AuthorityDTO save(AuthorityDTO authorityDTO) {
        AuthorityDTO result ;
        log.debug("Request to save Authority : {}", GsonUtils.entityToJson(authorityDTO));
        final Authority authority = authorityRepository.save(authorityMapper.toEntity(authorityDTO));
        result = authorityMapper.toDto(authority);

        return result;
    }

    @Override
    public Page<AuthorityDTO> findAll(String search, Pageable pageable) {
        log.debug("Request to get all Authorities");
        return authorityRepository.findAll( UtilsSpecification.<Authority>getSpecification(search), pageable).map(authorityMapper::toDtoLight);
    }

    @Override
    public Optional<AuthorityDTO> findOne(Long id) {
        log.debug("Request to get Authority : {}", id);
        return authorityRepository.findById(id).map(authorityMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Authority : {}", id);
        authorityRepository.deleteById(id);
    }
}
