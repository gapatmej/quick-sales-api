package ec.com.newsolutions.repository;

import ec.com.newsolutions.service.dto.BatchElectronicDocumentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CustomInvoiceClientRepository {

    Page<BatchElectronicDocumentDTO> getPendientElectronicDocument(Pageable pageable);
}
