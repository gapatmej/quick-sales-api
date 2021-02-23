package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.AddressCompany;
import ec.com.newsolutions.repository.AddressCompanyRepository;
import ec.com.newsolutions.service.AddressCompanyService;
import ec.com.newsolutions.service.dto.AddressCompanyDTO;
import ec.com.newsolutions.service.dto.CompanyDTO;
import ec.com.newsolutions.service.mapper.AddressCompanyMapper;
import net.logstash.logback.encoder.org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class AddressCompanyServiceImpl extends AbstractService implements AddressCompanyService {

    private final AddressCompanyRepository addressCompanyRepository;
    private final AddressCompanyMapper addressCompanyMapper;

    public AddressCompanyServiceImpl(AddressCompanyRepository addressCompanyRepository, AddressCompanyMapper addressCompanyMapper) {
        super(AddressCompanyServiceImpl.class);
        this.addressCompanyRepository = addressCompanyRepository;
        this.addressCompanyMapper = addressCompanyMapper;
    }

    @Override
    public AddressCompanyDTO save(AddressCompanyDTO addressCompanyDTO) {
        log.debug("Request to save Address Company : {}", addressCompanyDTO);
        AddressCompany addressCompany = save(addressCompanyMapper.toEntity(addressCompanyDTO));
        return addressCompanyMapper.toDto(addressCompany);
    }

    @Override
    public Page<AddressCompanyDTO> findAll(String search, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<AddressCompanyDTO> findOneDto(Long id) {
        return findOne(id).map(addressCompanyMapper::toDto);
    }

    @Override
    public AddressCompany save(AddressCompany addressCompany) {
        return addressCompanyRepository.save(addressCompany);
    }

    @Override
    public Optional<AddressCompany> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Address Company : {}", id);
        addressCompanyRepository.deleteById(id);
    }

    @Override
    public void deleteByCompany(Long companyId) {
        log.debug("Request to delete Address Company by companyId: {}", companyId);
        addressCompanyRepository.deleteByCompanyId(companyId);
    }

    @Override
    public List<AddressCompanyDTO> updateByCompany(CompanyDTO companyDTO) {
        log.debug("Request to save Addresses Company : {}", companyDTO.getAddressCompanies());

        List<AddressCompany> addressesCompany = addressCompanyRepository.findByCompanyId(companyDTO.getId());

        List<Long> newsId = companyDTO.getAddressCompanies().stream().filter(eP->eP.getId()!= null).map(AddressCompanyDTO::getId).collect(Collectors.toList());
        List<Long> oldsIdToDeleted = addressesCompany.stream().map(AddressCompany::getId).collect(Collectors.toList());
        oldsIdToDeleted.removeAll(newsId);

        addressCompanyRepository.deleteInBatch(addressesCompany.stream().filter(eP->oldsIdToDeleted.contains(eP.getId())).collect(Collectors.toList()));

        List<AddressCompanyDTO> result = new ArrayList<>();
        companyDTO.getAddressCompanies().forEach(aC->{
            result.add(save(aC));
        });

        return result;
    }
}
