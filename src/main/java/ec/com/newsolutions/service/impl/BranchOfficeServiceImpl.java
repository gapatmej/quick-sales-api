package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.service.BranchOfficeService;
import ec.com.newsolutions.domain.BranchOffice;
import ec.com.newsolutions.repository.BranchOfficeRepository;
import ec.com.newsolutions.service.dto.BranchOfficeDTO;
import ec.com.newsolutions.service.mapper.BranchOfficeMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BranchOfficeServiceImpl implements BranchOfficeService {

    private final BranchOfficeMapper branchOfficeMapper;
    private final Logger log = LoggerFactory.getLogger(BranchOfficeServiceImpl.class);
    private final BranchOfficeRepository branchOfficeRepository;

    public BranchOfficeServiceImpl(BranchOfficeMapper branchOfficeMapper, BranchOfficeRepository branchOfficeRepository) {
        this.branchOfficeMapper = branchOfficeMapper;
        this.branchOfficeRepository = branchOfficeRepository;
    }

    @Override
    public BranchOfficeDTO save(BranchOfficeDTO branchOfficeDTO) {
        log.debug("Request to save BranchOffice : {}", branchOfficeDTO);
        BranchOffice branchOffice =  branchOfficeRepository.save(branchOfficeMapper.toEntity(branchOfficeDTO));
        return branchOfficeMapper.toDto(branchOffice);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<BranchOfficeDTO> findAll(String search, Pageable pageable) {
        log.debug("Request to get all BranchOffices");
        return branchOfficeRepository.findAll( UtilsSpecification.<BranchOffice>getSpecification(search), pageable).map(branchOfficeMapper::toDto);
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
