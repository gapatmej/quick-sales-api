package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Organization;
import ec.com.newsolutions.service.dto.OrganizationDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrganizationMapper extends EntityMapper<OrganizationDTO, Organization> {

}
