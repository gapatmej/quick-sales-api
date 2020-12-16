package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.CompanyCategory;
import ec.com.newsolutions.service.dto.CompanyCategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CompanyCategoryMapper extends EntityMapperIgnoreAuditPropsAndPlus<CompanyCategoryDTO, CompanyCategory> {
}
