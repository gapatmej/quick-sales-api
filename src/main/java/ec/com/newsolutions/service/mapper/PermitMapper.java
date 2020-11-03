package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Permit;
import ec.com.newsolutions.service.dto.PermitDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PermitMapper extends EntityMapper<PermitDTO, Permit> {

    PermitDTO toDto(Permit permit);

    Permit toEntity(PermitDTO permitDTO);
}
