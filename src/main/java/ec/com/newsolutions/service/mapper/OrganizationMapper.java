package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Organization;
import ec.com.newsolutions.service.dto.OrganizationDTO;
import org.mapstruct.Mapper;

@Mapper(config = EntityMapperConfigIgnoreAuditProps.class)
public interface OrganizationMapper{

    OrganizationDTO toDto(Organization organization);

    Organization toEntity(OrganizationDTO organizationDTO);
}
