package ec.com.newsolutions.service;

import ec.com.newsolutions.service.dto.BatchElectronicDocumentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BatchElectronicDocumentService {

    Page<BatchElectronicDocumentDTO> getPendientInvoiceClient(Pageable pageable);
}
