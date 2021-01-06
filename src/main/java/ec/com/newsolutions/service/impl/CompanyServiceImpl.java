package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.service.AddressCompanyService;
import ec.com.newsolutions.service.CompanyService;
import ec.com.newsolutions.domain.Company;
import ec.com.newsolutions.repository.CompanyRepository;
import ec.com.newsolutions.service.dto.CompanyDTO;
import ec.com.newsolutions.service.mapper.CompanyMapper;

import ec.com.newsolutions.utils.GsonUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CompanyServiceImpl extends AbstractService implements CompanyService {

    private final CompanyMapper companyMapper;
    private final CompanyRepository companyRepository;
    private final AddressCompanyService addressCompanyService;

    public CompanyServiceImpl(CompanyMapper companyMapper, CompanyRepository companyRepository, AddressCompanyService addressCompanyService) {
        super(CompanyServiceImpl.class);
        this.companyMapper = companyMapper;
        this.companyRepository = companyRepository;
        this.addressCompanyService = addressCompanyService;
    }

    @Override
    public CompanyDTO save(CompanyDTO companyDTO) {
        log.debug("Request to save Company : {}", GsonUtils.entityToJson(companyDTO));
        final Company company = save(companyMapper.toEntity(companyDTO));
        CompanyDTO result = companyMapper.toDto(company);

        companyDTO.getAddressCompanies().forEach(aC -> aC.setCompanyId(company.getId()));
        result.setAddressCompanies(addressCompanyService.saveAll(companyDTO.getAddressCompanies()));

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CompanyDTO> findAll(String search, Pageable pageable) {
        log.debug("Request to get all Companies");
        return companyRepository.findAll( UtilsSpecification.<Company>getSpecificationWithWorkspace(search), pageable).map(companyMapper::toDtoLight);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CompanyDTO> findOneDto(Long id) {
        return findOne(id).map(companyMapper::toDto);
    }

    @Override
    public Company save(Company company) {
        Company result = companyRepository.save(company);
        return result;
    }

    @Override
    public Optional<Company> findOne(Long id) {
        log.debug("Request to get Company : {}", id);
        return companyRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Company : {}", id);
        addressCompanyService.deleteByCompany(id);
        companyRepository.deleteById(id);
    }
}
