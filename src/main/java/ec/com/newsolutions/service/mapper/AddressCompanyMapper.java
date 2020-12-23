package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.AddressCompany;
import ec.com.newsolutions.service.dto.AddressCompanyDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = EntityMapperConfigIgnoreAuditProps.class)
public interface AddressCompanyMapper {

    @Mapping(source = "company.id", target = "companyId")
    @Mapping(source = "province.id", target = "provinceId")
    @Mapping(source = "canton.id", target = "cantonId")
    @Mapping(source = "parish.id", target = "parishId")
    @Mapping(expression = "java(false)", target = "deleted")
    AddressCompanyDTO toDto(AddressCompany addressCompany);

    @Mapping(source = "companyId", target = "company.id")
    @Mapping(source = "provinceId", target = "province.id")
    @Mapping(source = "cantonId", target = "canton.id")
    @Mapping(source = "parishId", target = "parish.id")
    AddressCompany toEntity(AddressCompanyDTO addressCompanyDTO);
}
