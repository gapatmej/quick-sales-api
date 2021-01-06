package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.service.BranchOfficeService;
import ec.com.newsolutions.domain.BranchOffice;
import ec.com.newsolutions.repository.BranchOfficeRepository;
import ec.com.newsolutions.service.EmissionPointService;
import ec.com.newsolutions.service.InvoiceClientService;
import ec.com.newsolutions.service.dto.BranchOfficeDTO;

import ec.com.newsolutions.service.mapper.BranchOfficeMapper;
import ec.com.newsolutions.utils.GsonUtils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BranchOfficeServiceImpl extends AbstractService implements BranchOfficeService {

    private final BranchOfficeMapper branchOfficeMapper;
    private final BranchOfficeRepository branchOfficeRepository;

    private final EmissionPointService emissionPointService;

    public BranchOfficeServiceImpl(BranchOfficeMapper branchOfficeMapper, BranchOfficeRepository branchOfficeRepository, EmissionPointService emissionPointService) {
        super(BranchOfficeServiceImpl.class);
        this.branchOfficeMapper = branchOfficeMapper;
        this.branchOfficeRepository = branchOfficeRepository;
        this.emissionPointService = emissionPointService;
    }

    @Override
    public BranchOfficeDTO save(BranchOfficeDTO branchOfficeDTO) {
        log.debug("Request to save BranchOffice : {}", GsonUtils.entityToJson(branchOfficeDTO));
        BranchOffice branchOffice = save(branchOfficeMapper.toEntity(branchOfficeDTO));
        BranchOfficeDTO result = branchOfficeMapper.toDto(branchOffice);

        branchOfficeDTO.getEmissionPoints().forEach(eP -> eP.setBranchOfficeId(branchOffice.getId()));
        result.setEmissionPoints(emissionPointService.saveAll(branchOfficeDTO.getEmissionPoints()));

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BranchOfficeDTO> findAll(String search, Pageable pageable) {
        log.debug("Request to get all BranchOffices");
        return branchOfficeRepository.findAll( UtilsSpecification.<BranchOffice>getSpecificationWithWorkspace(search), pageable).map(branchOfficeMapper::toDtoLight);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BranchOfficeDTO> findAllWithoutWorkspace(String search, Pageable pageable) {
        log.debug("Request to get all BranchOffices");
        return branchOfficeRepository.findAll( UtilsSpecification.<BranchOffice>getSpecificationWithoutWorkspaceAndValidateSearch(search), pageable).map(branchOfficeMapper::toDtoLight);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<BranchOfficeDTO> findOneDto(Long id) {
        return findOne(id).map(branchOfficeMapper::toDto);
    }

    @Override
    public BranchOffice save(BranchOffice branchOffice) {
        BranchOffice result = branchOfficeRepository.save(branchOffice);
        return result;
    }

    @Override
    public Optional<BranchOffice> findOne(Long id) {
        log.debug("Request to get BranchOffice : {}", id);
        return branchOfficeRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete BranchOffice : {}", id);
        emissionPointService.deleteByBranchOffice(id);
        branchOfficeRepository.deleteById(id);
    }
}
