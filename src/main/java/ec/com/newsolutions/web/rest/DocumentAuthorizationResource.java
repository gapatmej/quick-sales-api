package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.domain.enumeration.BaseDocumentEnum;
import ec.com.newsolutions.service.DocumentAuthorizationService;
import ec.com.newsolutions.service.dto.DocumentAuthorizationDTO;
import ec.com.newsolutions.utils.Utils;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class DocumentAuthorizationResource extends AbstractResource {

    private final DocumentAuthorizationService documentAuthorizationService;

    public DocumentAuthorizationResource(DocumentAuthorizationService documentAuthorizationService) {
        super(DocumentAuthorizationResource.class, "bank");
        this.documentAuthorizationService = documentAuthorizationService;
    }

    @GetMapping("/document-authorization/find-by-base-document-and-emission-point")
    public ResponseEntity<DocumentAuthorizationDTO> findByBaseDocumentAndEmissionPoint(BaseDocumentEnum baseDocumentEnum,
                                                                                       Long emissionPointId) {
        log.debug("REST request to get a page of Document Authorization ");
        Optional<DocumentAuthorizationDTO> result = documentAuthorizationService
            .findByBaseDocumentAndEmissionPoint(baseDocumentEnum, emissionPointId);
        return ResponseUtil.wrapOrNotFound(result);
    }
}
