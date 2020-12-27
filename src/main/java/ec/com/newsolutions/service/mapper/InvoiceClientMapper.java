package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.service.dto.InvoiceClientDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = EntityMapperConfigIgnoreAuditProps.class, uses = {PaymentMapper.class, DetailInvoiceClientMapper.class})
public interface InvoiceClientMapper extends EntityMapperIgnoreAuditProps<InvoiceClientDTO, InvoiceClient> {

    @Mapping(target = "organizationId", source = "organization.id")
    @Mapping(target = "documentId", source = "document.id")
    @Mapping(target = "emissionPointId", source = "emissionPoint.id")
    @Mapping(target = "companyId", source = "company.id")
    InvoiceClientDTO toDto(InvoiceClient invoiceClient);

    @Mapping(target = "organization.id", source = "organizationId")
    @Mapping(target = "document.id", source = "documentId")
    @Mapping(target = "emissionPoint.id", source = "emissionPointId")
    @Mapping(target = "company.id", source = "companyId")
    @Mapping(target = "taxInvoices", ignore = true)
    @Mapping(target = "additionalInformations", ignore = true)
    InvoiceClient toEntity(InvoiceClientDTO invoiceClientDTO);

    @Mapping(target = "organizationId", source = "organization.id")
    @Mapping(target = "documentId", source = "document.id")
    @Mapping(target = "emissionPointId", source = "emissionPoint.id")
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "payments",  ignore = true)
    @Mapping(target = "detailInvoiceClients",  ignore = true)
    @Named(value = "light")
    InvoiceClientDTO toDtoLight(InvoiceClient invoiceClient);

    @IterableMapping(qualifiedByName = "light")
    List<InvoiceClientDTO> toDtoLight(List<InvoiceClient> invoiceClients);
}
