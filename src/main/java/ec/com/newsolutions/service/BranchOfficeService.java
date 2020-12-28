package ec.com.newsolutions.service;

import ec.com.newsolutions.service.dto.BranchOfficeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BranchOfficeService extends AbstractService<BranchOfficeDTO> {

    Optional<BranchOfficeDTO> findOneLight(Long id);
    Page<BranchOfficeDTO> findAllWithoutWorkspace(String search, Pageable pageable);
}
