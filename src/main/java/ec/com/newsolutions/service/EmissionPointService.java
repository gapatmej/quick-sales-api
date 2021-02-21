package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.EmissionPoint;
import ec.com.newsolutions.service.dto.BranchOfficeDTO;
import ec.com.newsolutions.service.dto.EmissionPointDTO;

import java.util.List;

public interface EmissionPointService extends AbstractServiceRest<EmissionPointDTO>, AbstractService<EmissionPoint> {

    void deleteByBranchOffice(Long branchOfficeId);
    List<EmissionPointDTO> updateByBranchOffice(BranchOfficeDTO branchOfficeDTO);
}
