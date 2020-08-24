package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.InvoiceClient;

public interface SRIElectronicDocumentService {

    void generateXML(InvoiceClient electronicDocument);

}
