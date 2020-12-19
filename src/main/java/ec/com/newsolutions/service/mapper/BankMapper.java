package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Bank;
import ec.com.newsolutions.domain.Unit;
import ec.com.newsolutions.service.dto.BankDTO;
import ec.com.newsolutions.service.dto.UnitDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BankMapper extends EntityMapperIgnoreAuditPropsAndPlus<BankDTO, Bank>  {
}
