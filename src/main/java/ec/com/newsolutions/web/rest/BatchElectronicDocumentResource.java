package ec.com.newsolutions.web.rest;


import ec.com.newsolutions.service.BatchElectronicDocumentService;
import ec.com.newsolutions.service.dto.BatchElectronicDocumentDTO;
import io.github.jhipster.web.util.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class BatchElectronicDocumentResource extends AbstractResource{

    private final BatchElectronicDocumentService batchElectronicDocumentService;

    public BatchElectronicDocumentResource(BatchElectronicDocumentService batchElectronicDocumentService) {
        super(BatchElectronicDocumentResource.class, "electronicDocument");
        this.batchElectronicDocumentService = batchElectronicDocumentService;
    }

    @GetMapping("/electronic-documents/pendient-invoice-client")
    public ResponseEntity<List<BatchElectronicDocumentDTO>> getPendientInvoiceClient(Pageable pageable) {
        log.debug("REST request to get a page of Tributary Documents");
        Page<BatchElectronicDocumentDTO> page = batchElectronicDocumentService.getPendientInvoiceClient(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
