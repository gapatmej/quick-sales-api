package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.InvoiceClient;

public interface DocumentElectronicService {

    void generateXML(InvoiceClient invoiceClient);
}
