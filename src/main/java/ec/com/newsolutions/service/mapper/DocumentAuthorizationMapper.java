package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.DocumentAuthorization;
import ec.com.newsolutions.service.dto.DocumentAuthorizationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = EntityMapperConfigIgnoreAuditProps.class, uses = {BranchOfficeMapper.class, EmissionPointMapper.class})
public interface DocumentAuthorizationMapper {

    @Mapping(source = "document.id", target = "documentId")
    @Mapping(source = "emissionPoint.branchOffice", target = "branchOffice", qualifiedByName = "light")
    @Mapping(expression = "java(false)", target = "deleted")
    DocumentAuthorizationDTO toDto(DocumentAuthorization documentAuthorization);

    @Mapping(source = "documentId", target = "document.id")
    DocumentAuthorization toEntity(DocumentAuthorizationDTO documentAuthorizationDTO);
}
