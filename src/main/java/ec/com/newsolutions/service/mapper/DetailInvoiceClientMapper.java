package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.DetailInvoiceClient;
import ec.com.newsolutions.service.dto.DetailInvoiceClientDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = EntityMapperConfigIgnoreAuditProps.class)
public interface DetailInvoiceClientMapper {

    @Mapping(source = "product.id", target = "productId")
    @Mapping(source = "invoiceClient.id", target = "invoiceClientId")
    @Mapping(expression = "java(false)", target = "deleted")
    DetailInvoiceClientDTO toDto(DetailInvoiceClient detailInvoiceClient);

    @Mapping(source = "productId", target = "product.id")
    @Mapping(source = "invoiceClientId", target = "invoiceClient.id")
    @Mapping( ignore = true, target = "taxesDetailInvoice")
    DetailInvoiceClient toEntity(DetailInvoiceClientDTO detailInvoiceClientDTO);
}


