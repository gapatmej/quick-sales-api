package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.AddressCompany;
import ec.com.newsolutions.repository.AddressCompanyRepository;
import ec.com.newsolutions.service.AddressCompanyService;
import ec.com.newsolutions.service.dto.AddressCompanyDTO;
import ec.com.newsolutions.service.mapper.AddressCompanyMapper;
import net.logstash.logback.encoder.org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

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
        AddressCompany addressCompany = addressCompanyRepository.save(addressCompanyMapper.toEntity(addressCompanyDTO));
        return addressCompanyMapper.toDto(addressCompany);
    }

    @Override
    public List<AddressCompanyDTO> saveAll(List<AddressCompanyDTO> addressCompanyDTOS) {
        log.debug("Request to save Address Company : {}", addressCompanyDTOS);
            List<AddressCompanyDTO> result = new ArrayList<>();
        addressCompanyDTOS.forEach(aC->{
            if(BooleanUtils.isTrue(aC.getDeleted())){
                delete(aC.getId());
            }else{
                result.add(save(aC));
            }
        });

        return result;
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
}
