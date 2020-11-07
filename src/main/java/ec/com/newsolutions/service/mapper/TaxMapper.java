package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Tax;
import ec.com.newsolutions.service.dto.TaxDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface TaxMapper extends EntityMapper<TaxDTO, Tax> {

    @Mapping(target = "organizationId", ignore = true)
    TaxDTO toDto(Tax tax);

    @Mapping(target = "organization.id", source = "organizationId")
    Tax toEntity(TaxDTO taxDTO);

    default Tax fromId(Long id){
        if(id ==null){
            return null;
        }

        Tax tax = new Tax();
        tax.setId(id);
        return tax;

    }
}
