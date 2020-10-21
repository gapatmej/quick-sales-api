package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.EmissionPoint;
import ec.com.newsolutions.service.dto.EmissionPointDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface EmissionPointMapper extends EntityMapper<EmissionPointDTO, EmissionPoint> {

    @Mapping(source = "branchOffice.id", target = "branchOfficeId")
    EmissionPointDTO toDto(EmissionPoint emissionPoint);

    @Mapping(source = "branchOfficeId", target = "branchOffice.id")
    EmissionPoint toEntity(EmissionPointDTO emissionPointDTO);
}
