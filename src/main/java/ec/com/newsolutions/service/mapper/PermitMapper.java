package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Permit;
import ec.com.newsolutions.service.dto.PermitDTO;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingInheritanceStrategy;

import java.util.List;

@Mapper(config = EntityMapperConfigIgnoreAuditProps.class, mappingInheritanceStrategy = MappingInheritanceStrategy.AUTO_INHERIT_ALL_FROM_CONFIG)
public interface PermitMapper  {

    @Mapping(target = "organizationId", source = "organization.id")
    PermitDTO toDto(Permit permit);

    @InheritConfiguration
    @Mapping(source = "organizationId", target = "organization.id")
    @Mapping(target = "authorities", ignore = true)
    Permit toEntity(PermitDTO permitDTO);

    List<PermitDTO> toDto(List<Permit> permits);
}
