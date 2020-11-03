package ec.com.newsolutions.service;

import ec.com.newsolutions.service.dto.BranchOfficeDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BranchOfficeService {

    BranchOfficeDTO save(BranchOfficeDTO branchOfficeDTO);

    Page<BranchOfficeDTO> findAll(String search, Pageable pageable);

    Optional<BranchOfficeDTO> findOne(Long id);

    void delete(Long id);
}
