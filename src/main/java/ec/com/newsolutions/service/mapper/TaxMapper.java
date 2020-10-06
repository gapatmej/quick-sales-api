package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Tax;
import ec.com.newsolutions.service.dto.TaxDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TaxMapper extends EntityMapper<TaxDTO, Tax> {
}
