package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.EmissionPoint;
import ec.com.newsolutions.service.dto.EmissionPointDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = EntityMapperConfigIgnoreAuditProps.class)
public interface EmissionPointMapper {

    @Mapping(source = "branchOffice.id", target = "branchOfficeId")
    @Mapping(expression = "java(false)", target = "deleted")
    EmissionPointDTO toDto(EmissionPoint emissionPoint);

    @Mapping(source = "branchOfficeId", target = "branchOffice.id")
    EmissionPoint toEntity(EmissionPointDTO emissionPointDTO);
}
