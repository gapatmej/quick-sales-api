package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.PayWay;
import ec.com.newsolutions.service.dto.PayWayDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PayWayMapper extends EntityMapperIgnoreAuditPropsAndPlus<PayWayDTO, PayWay>{
}
