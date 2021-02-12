package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.SriMessage;
import ec.com.newsolutions.service.dto.SriMessageDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = EntityMapperConfigIgnoreAuditProps.class)
public interface SriMessageMapper {

    @Mapping(source = "electronicDocument.id", target = "electronicDocumentId")
    SriMessageDTO toDto(SriMessage sriMessage);

    @Mapping(source = "electronicDocumentId", target = "electronicDocument.id")
    SriMessage toEntity(SriMessageDTO sriMessageDTO);
}
