package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.TaxInvoice;
import ec.com.newsolutions.service.dto.TaxInvoiceDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = EntityMapperConfigIgnoreAuditProps.class)
public interface TaxInvoiceMapper {

    @Mapping(source = "tax.id", target = "taxId")
    @Mapping(source = "invoiceClient.id", target = "invoiceClientId")
    TaxInvoiceDTO toDto(TaxInvoice taxInvoice);

    @Mapping(source = "taxId", target = "tax.id")
    @Mapping(source = "invoiceClientId", target = "invoiceClient.id")
    TaxInvoice toEntity(TaxInvoiceDTO taxInvoiceDTO);
}
