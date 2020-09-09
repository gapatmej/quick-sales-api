package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.Organization;

import ec.com.newsolutions.service.dto.OrganizationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

/**
 * Service Interface for managing {@link Organization}.
 */
public interface OrganizationService {

    OrganizationDTO save(OrganizationDTO organizationDTO);

    Page<OrganizationDTO> findAll(Pageable pageable);

    Optional<OrganizationDTO> findOne(Long id);

    void delete(Long id);
}
