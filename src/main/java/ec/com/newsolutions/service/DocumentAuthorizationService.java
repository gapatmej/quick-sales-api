package ec.com.newsolutions.service;

import ec.com.newsolutions.service.dto.DocumentAuthorizationDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface DocumentAuthorizationService {

    DocumentAuthorizationDTO save(DocumentAuthorizationDTO documentAuthorizationDTO);

    List<DocumentAuthorizationDTO> saveAll(List<DocumentAuthorizationDTO> documentAuthorizationDTOS);

    Page<DocumentAuthorizationDTO> findAll(String search, Pageable pageable);

    Optional<DocumentAuthorizationDTO> findOne(Long id);

    void delete(Long id);

    void deleteByDocument(Long branchOfficeId);
}
