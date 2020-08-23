package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.InvoiceClient;

public interface ElectronicDocumentService {

    void generateXML(InvoiceClient invoiceClient);
}
