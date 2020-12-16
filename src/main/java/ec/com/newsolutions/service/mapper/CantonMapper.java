package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Canton;
import ec.com.newsolutions.service.dto.CantonDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = EntityMapperConfigIgnoreAuditProps.class)
public interface CantonMapper {

    @Mapping(target = "provinceId", source = "province.id")
    CantonDTO toDto(Canton canton);

    @Mapping(target = "province.id", source = "provinceId")
    @Mapping(target = "parishes", ignore = true)
    Canton toEntity(CantonDTO cantonDTO);
}
