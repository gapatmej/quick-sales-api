package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.service.dto.BatchElectronicDocumentDTO;
import ec.com.newsolutions.service.dto.InvoiceClientDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface InvoiceClientService extends AbstractServiceRest<InvoiceClientDTO>, AbstractService<InvoiceClient> {
    void calculateTotals(InvoiceClient invoiceClient);
    InvoiceClientDTO calculateTotals(InvoiceClientDTO invoiceClient);
    Page<BatchElectronicDocumentDTO> getPendientElectronicDocument(Pageable pageable);
}
