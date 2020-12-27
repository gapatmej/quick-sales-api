package ec.com.newsolutions.service;

import ec.com.newsolutions.service.dto.BranchOfficeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BranchOfficeService extends AbstractService<BranchOfficeDTO> {
    Page<BranchOfficeDTO> findAllWithoutWorkspace(String search, Pageable pageable);
}
