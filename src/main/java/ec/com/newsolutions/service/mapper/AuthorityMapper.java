package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Authority;
import ec.com.newsolutions.service.dto.AuthorityDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface AuthorityMapper extends EntityMapper<AuthorityDTO, Authority> {

    @Mapping(target = "organizationId", ignore = true)
    AuthorityDTO toDto(Authority authority);

    @Mapping(target = "organization.id", source = "organizationId")
    Authority toEntity(AuthorityDTO authorityDTO);

    @Mapping(target = "organizationId", ignore = true)
    @Mapping(target = "permits",  ignore = true)
    @Named(value = "light")
    AuthorityDTO toDtoLight(Authority authority);

    @IterableMapping(qualifiedByName = "light")
    List<AuthorityDTO> toDtoLight(List<Authority> authorities);

}
