package ec.com.newsolutions.service;

import ec.com.newsolutions.service.dto.AddressCompanyDTO;

import java.util.List;


public interface AddressCompanyService {

    AddressCompanyDTO save(AddressCompanyDTO addressCompanyDTO);
    List<AddressCompanyDTO> saveAll(List<AddressCompanyDTO> addressCompanyDTOS);
    void delete(Long id);
    void deleteByCompany(Long companyId);
}
