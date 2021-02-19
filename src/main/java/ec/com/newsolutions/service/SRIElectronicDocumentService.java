package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.domain.TributaryDocument;
import ec.com.newsolutions.service.errors.ElectronicDocumentException;
import ec.com.newsolutions.utils.electronicdocuments.Signature;
import ec.com.newsolutions.web.wsdl.sri.authorization.Autorizacion;

public interface SRIElectronicDocumentService {

    void sendInvoiceToSRI(InvoiceClient invoiceClient);
    void generateInvoiceClientXML(InvoiceClient invoiceClient) throws ElectronicDocumentException;
    void sign(Signature signature) throws ElectronicDocumentException;
    void receive(TributaryDocument tributaryDocument) throws ElectronicDocumentException;
    Autorizacion authorize(TributaryDocument tributaryDocument) throws ElectronicDocumentException;
}
