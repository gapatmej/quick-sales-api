package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.ElectronicDocument;
import ec.com.newsolutions.service.dto.ElectronicDocumentDTO;
import org.mapstruct.Mapper;

@Mapper(config = EntityMapperConfigIgnoreAuditProps.class, uses = {SriMessageMapper.class})
public interface ElectronicDocumentMapper extends EntityMapperIgnoreAuditProps<ElectronicDocumentDTO, ElectronicDocument> {

    ElectronicDocumentDTO toDto(ElectronicDocument electronicDocument);

    ElectronicDocument toEntity(ElectronicDocumentDTO electronicDocumentDTO);
}
