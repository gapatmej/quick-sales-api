package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.Company;

import ec.com.newsolutions.service.dto.CompanyDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CompanyService {


    CompanyDTO save(CompanyDTO companyDTO);

    Page<CompanyDTO> findAll(String search, Pageable pageable);

    Optional<CompanyDTO> findOne(Long id);

    void delete(Long id);
}
