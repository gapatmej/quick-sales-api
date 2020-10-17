package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.BranchOffice;
import ec.com.newsolutions.service.dto.BranchOfficeDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface BranchOfficeMapper extends EntityMapper<BranchOfficeDTO, BranchOffice> {
}
