package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Unit;
import ec.com.newsolutions.service.dto.UnitDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UnitMapper extends EntityMapper<UnitDTO, Unit> {
}
