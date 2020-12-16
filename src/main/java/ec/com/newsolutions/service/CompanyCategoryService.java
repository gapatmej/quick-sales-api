package ec.com.newsolutions.service;

import ec.com.newsolutions.service.dto.CompanyCategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CompanyCategoryService {

    CompanyCategoryDTO save(CompanyCategoryDTO companyCategoryDTO);

    Page<CompanyCategoryDTO> findAll(String search, Pageable pageable);

    Optional<CompanyCategoryDTO> findOne(Long id);

    void delete(Long id);
}
