package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.DocumentAuthorization;
import ec.com.newsolutions.domain.enumeration.BaseDocumentEnum;
import ec.com.newsolutions.repository.DocumentAuthorizationRepository;
import ec.com.newsolutions.service.DocumentAuthorizationService;
import ec.com.newsolutions.service.dto.DocumentAuthorizationDTO;
import ec.com.newsolutions.service.dto.DocumentDTO;
import ec.com.newsolutions.service.mapper.DocumentAuthorizationMapper;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class DocumentAuthorizationServiceImpl extends AbstractService implements DocumentAuthorizationService {

    private final DocumentAuthorizationMapper documentAuthorizationMapper;
    private final DocumentAuthorizationRepository documentAuthorizationRepository;

    public DocumentAuthorizationServiceImpl(DocumentAuthorizationMapper documentAuthorizationMapper, DocumentAuthorizationRepository documentAuthorizationRepository) {
        super(DocumentAuthorizationServiceImpl.class);
        this.documentAuthorizationMapper = documentAuthorizationMapper;
        this.documentAuthorizationRepository = documentAuthorizationRepository;
    }

    @Override
    public DocumentAuthorizationDTO save(DocumentAuthorizationDTO documentAuthorizationDTO) {
        log.debug("Request to save Document Authorization : {}", documentAuthorizationDTO);
        DocumentAuthorization documentAuthorization = save(documentAuthorizationMapper.toEntity(documentAuthorizationDTO));
        return documentAuthorizationMapper.toDto(documentAuthorization);
    }

    @Override
    public Page<DocumentAuthorizationDTO> findAll(String search, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<DocumentAuthorizationDTO> findOneDto(Long id) {
        return findOne(id).map(documentAuthorizationMapper::toDto);
    }

    @Override
    public DocumentAuthorization save(DocumentAuthorization documentAuthorization) {
        DocumentAuthorization result = documentAuthorizationRepository.save(documentAuthorization);
        return result;
    }

    @Override
    public Optional<DocumentAuthorization> findOne(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<DocumentAuthorization> findByDocumentIdAndEmissionPointId(Long documentId, Long emissionPointId) {
        log.debug("Request to get Document Authorization by documentId :{}, emissionPointId : {}", documentId, emissionPointId);
        return documentAuthorizationRepository.findByDocumentIdAndEmissionPointId(documentId,emissionPointId);
    }

    @Override
    public Optional<DocumentAuthorizationDTO> findByBaseDocumentAndEmissionPoint(BaseDocumentEnum baseDocumentEnum, Long emissionPointId) {
        log.debug("Request to get Document Authorization by baseDocument :{}, emissionPointId : {}", baseDocumentEnum, emissionPointId);
        return documentAuthorizationRepository.findByDocumentBaseDocumentAndEmissionPointId(baseDocumentEnum,emissionPointId).map(documentAuthorizationMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Document Authorization : {}", id);
        documentAuthorizationRepository.deleteById(id);
    }

    @Override
    public void deleteByDocument(Long idDocument) {
        documentAuthorizationRepository.deleteByDocumentId(idDocument);
    }

    @Override
    public List<DocumentAuthorizationDTO> updateByDocument(DocumentDTO documentDTO) {
        log.debug("Request to save Document Authorizations : {}", documentDTO.getDocumentAuthorizations());

        List<DocumentAuthorization> documentAuthorizations = documentAuthorizationRepository.findByDocumentId(documentDTO.getId());

        List<Long> newsId = documentDTO.getDocumentAuthorizations().stream().filter(eP->eP.getId()!= null).map(DocumentAuthorizationDTO::getId).collect(Collectors.toList());
        List<Long> oldsIdToDeleted = documentAuthorizations.stream().map(DocumentAuthorization::getId).collect(Collectors.toList());
        oldsIdToDeleted.removeAll(newsId);

        documentAuthorizationRepository.deleteInBatch(documentAuthorizations.stream().filter(eP->oldsIdToDeleted.contains(eP.getId())).collect(Collectors.toList()));

        List<DocumentAuthorizationDTO> result = new ArrayList<>();
        documentDTO.getDocumentAuthorizations().forEach(ep->{
            result.add(save(ep));
        });

        return result;
    }
}
