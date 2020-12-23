package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.Document;
import ec.com.newsolutions.repository.DocumentRepository;
import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.service.DocumentAuthorizationService;
import ec.com.newsolutions.service.DocumentService;
import ec.com.newsolutions.service.dto.DocumentDTO;
import ec.com.newsolutions.service.mapper.DocumentMapper;
import ec.com.newsolutions.utils.GsonUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class DocumentServiceImpl extends AbstractService implements DocumentService {

    private final DocumentMapper documentMapper;
    private final DocumentRepository documentRepository;
    private final DocumentAuthorizationService documentAuthorizationService;

    public DocumentServiceImpl(DocumentMapper documentMapper, DocumentRepository documentRepository, DocumentAuthorizationService documentAuthorizationService) {
        super(DocumentServiceImpl.class);
        this.documentMapper = documentMapper;
        this.documentRepository = documentRepository;
        this.documentAuthorizationService = documentAuthorizationService;
    }

    @Override
    public DocumentDTO save(DocumentDTO documentDTO) {
        DocumentDTO result ;
        log.debug("Request to save Document : {}", GsonUtils.entityToJson(documentDTO));
        final Document document = documentRepository.save(documentMapper.toEntity(documentDTO));
        result = documentMapper.toDto(document);

        documentDTO.getDocumentAuthorizations().forEach(dA -> dA.setDocumentId(document.getId()));
        result.setDocumentAuthorizations(documentAuthorizationService.saveAll(documentDTO.getDocumentAuthorizations()));
        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<DocumentDTO> findAll(String search, Pageable pageable) {
        log.debug("Request to get all Documents");
        return documentRepository.findAll( UtilsSpecification.<Document>getSpecificationWithWorkspace(search), pageable).map(documentMapper::toDtoLight);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<DocumentDTO> findOne(Long id) {
        log.debug("Request to get Document : {}", id);
        return documentRepository.findById(id).map(documentMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Document : {}", id);
        documentAuthorizationService.deleteByDocument(id);
        documentRepository.deleteById(id);

    }
}
