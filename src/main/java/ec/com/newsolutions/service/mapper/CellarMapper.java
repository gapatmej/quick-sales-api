package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Cellar;
import ec.com.newsolutions.service.dto.CellarDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CellarMapper extends EntityMapper<CellarDTO, Cellar> {

    @Mapping(target = "organizationId", ignore = true)
    CellarDTO toDto(Cellar cellar);

    @Mapping(target = "organization.id", source = "organizationId")
    Cellar toEntity(CellarDTO cellarDTO);
}
