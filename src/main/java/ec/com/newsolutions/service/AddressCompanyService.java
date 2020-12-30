package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.AddressCompany;
import ec.com.newsolutions.service.dto.AddressCompanyDTO;
import java.util.List;

public interface AddressCompanyService extends AbstractService<AddressCompanyDTO, AddressCompany> {

    List<AddressCompanyDTO> saveAll(List<AddressCompanyDTO> addressCompanyDTOS);
    void deleteByCompany(Long companyId);
}
