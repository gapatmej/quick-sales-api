package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Cellar;
import ec.com.newsolutions.service.dto.CellarDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CellarMapper extends EntityMapper<CellarDTO, Cellar> {
}
