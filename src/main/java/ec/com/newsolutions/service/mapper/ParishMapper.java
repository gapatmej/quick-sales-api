package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Parish;
import ec.com.newsolutions.service.dto.ParishDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = EntityMapperConfigIgnoreAuditProps.class)
public interface ParishMapper  {

    @Mapping(target = "cantonId", source = "canton.id")
    ParishDTO toDto(Parish parish);

    @Mapping(target = "canton.id", source = "cantonId")
    Parish toEntity(ParishDTO parishDTO);
}
