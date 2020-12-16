package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.CompanyCategory;
import ec.com.newsolutions.repository.CompanyCategoryRepository;
import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.service.CompanyCategoryService;
import ec.com.newsolutions.service.dto.CompanyCategoryDTO;
import ec.com.newsolutions.service.mapper.CompanyCategoryMapper;
import ec.com.newsolutions.utils.GsonUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CompanyCategoryServiceImpl extends AbstractService implements CompanyCategoryService {

    private final CompanyCategoryMapper companyCategoryMapper;
    private final CompanyCategoryRepository companyCategoryRepository;

    public CompanyCategoryServiceImpl(CompanyCategoryMapper companyCategoryMapper, CompanyCategoryRepository companyCategoryRepository) {
        super(CompanyCategoryServiceImpl.class);
        this.companyCategoryMapper = companyCategoryMapper;
        this.companyCategoryRepository = companyCategoryRepository;
    }

    @Override
    public CompanyCategoryDTO save(CompanyCategoryDTO companyCategoryDTO) {
        log.debug("Request to save Company Category : {}", GsonUtils.entityToJson(companyCategoryDTO));
        CompanyCategory companyCategory =  companyCategoryRepository.save(companyCategoryMapper.toEntity(companyCategoryDTO));
        return companyCategoryMapper.toDto(companyCategory);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CompanyCategoryDTO> findAll(String search, Pageable pageable) {
        return companyCategoryRepository.findAll( UtilsSpecification.<CompanyCategory>getSpecificationWithWorkspace(search), pageable).map(companyCategoryMapper::toDto);

    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CompanyCategoryDTO> findOne(Long id) {
        log.debug("Request to get Company Category : {}", id);
        return companyCategoryRepository.findById(id).map(companyCategoryMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Company Category : {}", id);
        companyCategoryRepository.deleteById(id);
    }
}
