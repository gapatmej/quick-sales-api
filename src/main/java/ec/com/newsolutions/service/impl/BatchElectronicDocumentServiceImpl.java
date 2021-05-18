package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.service.BatchElectronicDocumentService;
import ec.com.newsolutions.service.InvoiceClientService;
import ec.com.newsolutions.service.dto.BatchElectronicDocumentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional
public class BatchElectronicDocumentServiceImpl extends AbstractService implements BatchElectronicDocumentService {

    private final InvoiceClientService invoiceClientService;

    public BatchElectronicDocumentServiceImpl(InvoiceClientService invoiceClientService) {
        super(BatchElectronicDocumentServiceImpl.class);
        this.invoiceClientService = invoiceClientService;
    }

    @Override
    public Page<BatchElectronicDocumentDTO> getPendientInvoiceClient(Pageable pageable) {
        return invoiceClientService.getPendientElectronicDocument(pageable);
    }
}
