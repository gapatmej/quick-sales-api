package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.service.OrganizationService;
import ec.com.newsolutions.domain.Organization;
import ec.com.newsolutions.repository.OrganizationRepository;
import ec.com.newsolutions.service.dto.OrganizationDTO;
import ec.com.newsolutions.service.mapper.OrganizationMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class OrganizationServiceImpl implements OrganizationService {

    private final OrganizationMapper organizationMapper;
    private final Logger log = LoggerFactory.getLogger(OrganizationServiceImpl.class);

    private final OrganizationRepository organizationRepository;

    public OrganizationServiceImpl(OrganizationMapper organizationMapper, OrganizationRepository organizationRepository) {
        this.organizationMapper = organizationMapper;
        this.organizationRepository = organizationRepository;
    }

    @Override
    public OrganizationDTO save(OrganizationDTO organizationDTO) {
        log.debug("Request to save Organization : {}", organizationDTO);
        Organization organization =  organizationRepository.save(organizationMapper.toEntity(organizationDTO));
        return organizationMapper.toDto(organization);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<OrganizationDTO> findAll(String search, Pageable pageable) {
        log.debug("Request to get all Organizations");
        return organizationRepository.findAll( UtilsSpecification.<Organization>getSpecification(search), pageable).map(organizationMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<OrganizationDTO> findOne(Long id) {
        log.debug("Request to get Organization : {}", id);
        return organizationRepository.findById(id).map(organizationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Organization : {}", id);
        organizationRepository.deleteById(id);
    }
}
