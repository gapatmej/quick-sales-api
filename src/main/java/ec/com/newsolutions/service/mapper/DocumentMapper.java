package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Document;
import ec.com.newsolutions.service.dto.DocumentDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = EntityMapperConfigIgnoreAuditProps.class, uses = {DocumentAuthorizationMapper.class})
public interface DocumentMapper extends EntityMapperIgnoreAuditProps<DocumentDTO, Document> {

    @Mapping(target = "organizationId", source = "organization.id")
    DocumentDTO toDto(Document document);

    @Mapping(target = "organization.id", source = "organizationId")
    Document toEntity(DocumentDTO documentDTO);

    @Mapping(target = "organizationId", source = "organization.id")
    @Mapping(target = "documentAuthorizations",  ignore = true)
    @Named(value = "light")
    DocumentDTO toDtoLight(Document document);

    @IterableMapping(qualifiedByName = "light")
    List<DocumentDTO> toDtoLight(List<Document> documents);
}
