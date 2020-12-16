package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.BranchOffice;
import ec.com.newsolutions.service.dto.BranchOfficeDTO;
import org.mapstruct.*;

import java.util.List;

@Mapper(config = EntityMapperConfigIgnoreAuditProps.class, uses = {EmissionPointMapper.class})
public interface BranchOfficeMapper extends EntityMapperIgnoreAuditProps<BranchOfficeDTO, BranchOffice> {

    @Mapping(target = "organizationId", source = "organization.id")
    BranchOfficeDTO toDto(BranchOffice branchOffice);

    @Mapping(target = "organization.id", source = "organizationId")
    BranchOffice toEntity(BranchOfficeDTO branchOfficeDTO);

    @Mapping(target = "organizationId", source = "organization.id")
    @Mapping(target = "emissionPoints",  ignore = true)
    @Named(value = "light")
    BranchOfficeDTO toDtoLight(BranchOffice branchOffice);

    @IterableMapping(qualifiedByName = "light")
    List<BranchOfficeDTO> toDtoLight(List<BranchOffice> BranchOffices);

}
