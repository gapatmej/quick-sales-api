package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.DocumentAuthorization;
import ec.com.newsolutions.repository.DocumentAuthorizationRepository;
import ec.com.newsolutions.service.DocumentAuthorizationService;
import ec.com.newsolutions.service.dto.DocumentAuthorizationDTO;
import ec.com.newsolutions.service.mapper.DocumentAuthorizationMapper;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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
        DocumentAuthorization documentAuthorization = documentAuthorizationRepository.save(documentAuthorizationMapper.toEntity(documentAuthorizationDTO));
        return documentAuthorizationMapper.toDto(documentAuthorization);
    }

    @Override
    public List<DocumentAuthorizationDTO> saveAll(List<DocumentAuthorizationDTO> documentAuthorizationDTOS) {
        log.debug("Request to save Document Authorizations : {}", documentAuthorizationDTOS);
        List<DocumentAuthorizationDTO> result = new ArrayList<>();
        documentAuthorizationDTOS.forEach(dA->{
            if(BooleanUtils.isTrue(dA.isDeleted())){
                delete(dA.getId());
            }else{
                result.add(save(dA));
            }
        });

        return result;
    }

    @Override
    public Page<DocumentAuthorizationDTO> findAll(String search, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<DocumentAuthorizationDTO> findOne(Long id) {
        return Optional.empty();
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
}
