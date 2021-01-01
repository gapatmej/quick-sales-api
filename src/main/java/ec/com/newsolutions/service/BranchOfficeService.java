package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.BranchOffice;
import ec.com.newsolutions.service.dto.BranchOfficeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BranchOfficeService extends AbstractServiceRest<BranchOfficeDTO>, AbstractService<BranchOffice> {

    Page<BranchOfficeDTO> findAllWithoutWorkspace(String search, Pageable pageable);
}
