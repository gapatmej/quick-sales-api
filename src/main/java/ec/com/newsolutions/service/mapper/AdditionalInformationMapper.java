package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.AdditionalInformation;
import ec.com.newsolutions.service.dto.AdditionalInformationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = EntityMapperConfigIgnoreAuditProps.class)
public interface AdditionalInformationMapper {

    @Mapping(source = "invoiceClient.id", target = "invoiceClientId")
    AdditionalInformationDTO toDto(AdditionalInformation additionalInformation);

    @Mapping(source = "invoiceClientId", target = "invoiceClient.id")
    AdditionalInformation toEntity(AdditionalInformationDTO additionalInformationDTO);
}
