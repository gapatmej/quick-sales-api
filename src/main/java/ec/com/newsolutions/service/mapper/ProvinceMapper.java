package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Province;
import ec.com.newsolutions.service.dto.ProvinceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = EntityMapperConfigIgnoreAuditProps.class)
public interface ProvinceMapper {

    @Mapping(target = "organizationId", source = "organization.id")
    ProvinceDTO toDto(Province province);

    @Mapping(target = "organization.id", source = "organizationId")
    @Mapping(target = "cantons", ignore = true)
    Province toEntity(ProvinceDTO provinceDTO);
}
