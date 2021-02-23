package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.AddressCompany;
import ec.com.newsolutions.service.dto.AddressCompanyDTO;
import ec.com.newsolutions.service.dto.CompanyDTO;

import java.util.List;

public interface AddressCompanyService extends AbstractServiceRest<AddressCompanyDTO>, AbstractService<AddressCompany> {

    void deleteByCompany(Long companyId);
    List<AddressCompanyDTO> updateByCompany(CompanyDTO companyDTO);
}
