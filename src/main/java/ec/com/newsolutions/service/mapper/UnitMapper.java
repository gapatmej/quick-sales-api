package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Unit;
import ec.com.newsolutions.service.dto.UnitDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UnitMapper extends EntityMapper<UnitDTO, Unit> {

    @Mapping(target = "organizationId", ignore = true)
    UnitDTO toDto(Unit unit);

    @Mapping(target = "organization.id", source = "organizationId")
    Unit toEntity(UnitDTO unitDTO);
}
