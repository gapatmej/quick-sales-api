package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Company;
import ec.com.newsolutions.service.dto.CompanyDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(componentModel = "spring", uses = {AddressCompanyMapper.class})
public interface CompanyMapper {

    @Mapping(target = "organizationId", source = "organization.id")
    @Mapping(target = "companyCategoryId", source = "companyCategory.id")
    CompanyDTO toDto(Company company);

    @Mapping(target = "organization.id", source = "organizationId")
    @Mapping(target = "companyCategory.id", source = "companyCategoryId")
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Company toEntity(CompanyDTO companyDTO);

    @Mapping(target = "organizationId", source = "organization.id")
    @Mapping(target = "companyCategoryId", source = "companyCategory.id")
    @Mapping(target = "addressCompanies",  ignore = true)
    @Named(value = "light")
    CompanyDTO toDtoLight(Company company);

    @IterableMapping(qualifiedByName = "light")
    List<CompanyDTO> toDtoLight(List<Company> companies);

}
