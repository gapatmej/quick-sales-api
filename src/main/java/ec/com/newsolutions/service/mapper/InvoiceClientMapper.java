package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.ElectronicDocument;
import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.service.dto.InvoiceClientDTO;
import org.mapstruct.IterableMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.List;

@Mapper(config = EntityMapperConfigIgnoreAuditProps.class, uses = { AdditionalInformationMapper.class,
    DetailInvoiceClientMapper.class, PaymentMapper.class, ElectronicDocument.class})
public interface InvoiceClientMapper extends EntityMapperIgnoreAuditProps<InvoiceClientDTO, InvoiceClient> {

    @Mapping(target = "electronicDocumentId", source = "electronicDocument.id")
    @Mapping(target = "documentId", source = "document.id")
    @Mapping(target = "organizationId", source = "organization.id")
    @Mapping(target = "branchOfficeId", source = "branchOffice.id")
    @Mapping(target = "emissionPointId", source = "emissionPoint.id")
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "addressCompanyId", source = "addressCompany.id")
    InvoiceClientDTO toDto(InvoiceClient invoiceClient);

    @Mapping(target = "electronicDocument.id", source = "electronicDocumentId")
    @Mapping(target = "document.id", source = "documentId")
    @Mapping(target = "organization.id", source = "organizationId")
    @Mapping(target = "branchOffice.id", source = "branchOfficeId")
    @Mapping(target = "emissionPoint.id", source = "emissionPointId")
    @Mapping(target = "company.id", source = "companyId")
    @Mapping(target = "addressCompany.id", source = "addressCompanyId")
    @Mapping(target = "taxesInvoice", ignore = true)
    InvoiceClient toEntity(InvoiceClientDTO invoiceClientDTO);

    @Mapping(target = "electronicDocumentId", source = "electronicDocument.id")
    @Mapping(target = "documentId", source = "document.id")
    @Mapping(target = "organizationId", source = "organization.id")
    @Mapping(target = "branchOfficeId", source = "branchOffice.id")
    @Mapping(target = "emissionPointId", source = "emissionPoint.id")
    @Mapping(target = "companyId", source = "company.id")
    @Mapping(target = "addressCompanyId", source = "addressCompany.id")
    @Mapping(target = "payments",  ignore = true)
    @Mapping(target = "detailsInvoiceClient",  ignore = true)
    @Named(value = "light")
    InvoiceClientDTO toDtoLight(InvoiceClient invoiceClient);

    @IterableMapping(qualifiedByName = "light")
    List<InvoiceClientDTO> toDtoLight(List<InvoiceClient> invoiceClients);
}
