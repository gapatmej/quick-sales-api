package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.Document;
import ec.com.newsolutions.service.dto.DocumentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface DocumentService extends AbstractService<DocumentDTO, Document> {

}
