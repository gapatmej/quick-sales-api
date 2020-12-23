package ec.com.newsolutions.service;

import ec.com.newsolutions.service.dto.DocumentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DocumentService {

    DocumentDTO save(DocumentDTO documentDTO);

    Page<DocumentDTO> findAll(String search, Pageable pageable);

    Optional<DocumentDTO> findOne(Long id);

    void delete(Long id);
}
