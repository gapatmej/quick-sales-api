package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.Organization;
import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.service.BranchOfficeService;
import ec.com.newsolutions.domain.BranchOffice;
import ec.com.newsolutions.repository.BranchOfficeRepository;
import ec.com.newsolutions.service.EmissionPointService;
import ec.com.newsolutions.service.dto.BranchOfficeDTO;
import ec.com.newsolutions.service.dto.EmissionPointDTO;
import ec.com.newsolutions.service.mapper.BranchOfficeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class BranchOfficeServiceImpl implements BranchOfficeService {

    private final BranchOfficeMapper branchOfficeMapper;
    private final Logger log = LoggerFactory.getLogger(BranchOfficeServiceImpl.class);
    private final BranchOfficeRepository branchOfficeRepository;

    private final EmissionPointService emissionPointService;

    public BranchOfficeServiceImpl(BranchOfficeMapper branchOfficeMapper, BranchOfficeRepository branchOfficeRepository, EmissionPointService emissionPointService) {
        this.branchOfficeMapper = branchOfficeMapper;
        this.branchOfficeRepository = branchOfficeRepository;
        this.emissionPointService = emissionPointService;
    }

    @Override
    public BranchOfficeDTO save(BranchOfficeDTO branchOfficeDTO) {
        BranchOfficeDTO result = new BranchOfficeDTO();
        log.debug("Request to save BranchOffice : {}", branchOfficeDTO);

        BranchOffice branchOfficefirst = branchOfficeMapper.toEntity(branchOfficeDTO);
        Organization organization = new Organization();
        organization.setId(new Long(2501));
        branchOfficefirst.setOrganization(organization);
        final BranchOffice branchOffice = branchOfficeRepository.save(branchOfficefirst);

        branchOfficeDTO.getEmissionPoints().forEach(eP -> eP.setBranchOfficeId(branchOffice.getId()));

        result = branchOfficeMapper.toDto(branchOffice);
        result.setEmissionPoints(emissionPointService.saveAll(branchOfficeDTO.getEmissionPoints()));
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BranchOfficeDTO> findAll(String search, Pageable pageable) {
        log.debug("Request to get all BranchOffices");
        return branchOfficeRepository.findAll( UtilsSpecification.<BranchOffice>getSpecification(search), pageable).map(branchOfficeMapper::toDtoLight);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BranchOfficeDTO> findOne(Long id) {
        log.debug("Request to get BranchOffice : {}", id);
        return branchOfficeRepository.findById(id).map(branchOfficeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BranchOffice : {}", id);
        branchOfficeRepository.deleteById(id);
    }
}
