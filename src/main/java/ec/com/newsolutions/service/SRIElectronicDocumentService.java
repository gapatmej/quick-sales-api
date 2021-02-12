package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.service.errors.ElectronicDocumentException;
import ec.com.newsolutions.utils.electronicdocuments.Signature;

public interface SRIElectronicDocumentService {

    void generateXML(InvoiceClient invoiceClient) throws ElectronicDocumentException;
    void sign(Signature signature) throws ElectronicDocumentException;
    void receive(InvoiceClient invoiceClient) throws ElectronicDocumentException;
    void authorize(InvoiceClient invoiceClient) throws ElectronicDocumentException;
}
