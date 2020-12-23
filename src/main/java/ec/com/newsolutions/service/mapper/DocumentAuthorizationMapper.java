package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.DocumentAuthorization;
import ec.com.newsolutions.service.dto.DocumentAuthorizationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = EntityMapperConfigIgnoreAuditProps.class)
public interface DocumentAuthorizationMapper {

    @Mapping(source = "document.id", target = "documentId")
    @Mapping(source = "emissionPoint.id", target = "emissionPointId")
    @Mapping(expression = "java(false)", target = "deleted")
    DocumentAuthorizationDTO toDto(DocumentAuthorization documentAuthorization);

    @Mapping(source = "documentId", target = "document.id")
    @Mapping(source = "emissionPointId", target = "emissionPoint.id")
    DocumentAuthorization toEntity(DocumentAuthorizationDTO documentAuthorizationDTO);
}
