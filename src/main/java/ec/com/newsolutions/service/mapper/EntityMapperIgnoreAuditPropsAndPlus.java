package ec.com.newsolutions.service.mapper;

import org.mapstruct.Mapping;

import java.util.List;

public interface EntityMapperIgnoreAuditPropsAndPlus<D, E>{

    @Mapping(target = "organization.id", source = "organizationId")
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    E toEntity(D dto);

    @Mapping(target = "organizationId", source = "organization.id")
    D toDto(E entity);

    List<E> toEntity(List<D> dtoList);

    List <D> toDto(List<E> entityList);
}
