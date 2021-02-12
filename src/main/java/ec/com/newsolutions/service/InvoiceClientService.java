package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.domain.enumeration.SRIDocumentStateEnum;
import ec.com.newsolutions.service.dto.InvoiceClientDTO;

public interface InvoiceClientService extends AbstractServiceRest<InvoiceClientDTO>, AbstractService<InvoiceClient> {
    void calculateTotals(InvoiceClient invoiceClient);
}
