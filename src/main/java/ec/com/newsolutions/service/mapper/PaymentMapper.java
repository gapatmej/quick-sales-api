package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Payment;
import ec.com.newsolutions.service.dto.PaymentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = EntityMapperConfigIgnoreAuditProps.class)
public interface PaymentMapper {

    @Mapping(source = "payWay.id", target = "payWayId")
    @Mapping(source = "invoiceClient.id", target = "invoiceClientId")
    PaymentDTO toDto(Payment payment);

    @Mapping(source = "payWayId", target = "payWay.id")
    @Mapping(source = "invoiceClientId", target = "invoiceClient.id")
    Payment toEntity(PaymentDTO paymentDTO);
}
