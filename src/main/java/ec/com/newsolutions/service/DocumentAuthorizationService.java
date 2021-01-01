package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.DocumentAuthorization;
import ec.com.newsolutions.service.dto.DocumentAuthorizationDTO;

import java.util.List;
import java.util.Optional;

public interface DocumentAuthorizationService extends AbstractServiceRest<DocumentAuthorizationDTO>, AbstractService<DocumentAuthorization> {

    List<DocumentAuthorizationDTO> saveAll(List<DocumentAuthorizationDTO> documentAuthorizationDTOS);
    Optional<DocumentAuthorization>  findByDocumentIdAndEmissionPointId(Long documentId, Long emissionPointId);
    void deleteByDocument(Long branchOfficeId);
}
