package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.AbstractMainEntity;
import ec.com.newsolutions.service.dto.AbstractMainDTO;
import org.mapstruct.MapperConfig;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

import java.util.List;

@MapperConfig(componentModel = "spring", mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_FROM_CONFIG)
public interface EntityMapperConfigIgnoreAuditProps<D extends AbstractMainDTO, E extends AbstractMainEntity> {

    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    E toEntity(D dto);

    D toDto(E entity);

}
