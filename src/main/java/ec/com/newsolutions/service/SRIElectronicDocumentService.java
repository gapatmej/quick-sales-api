package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.ElectronicDocument;
import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.service.errors.ElectronicDocumentException;
import ec.com.newsolutions.utils.electronicdocuments.Signature;
import ec.com.newsolutions.web.wsdl.receptionSRI.RespuestaSolicitud;

import java.io.File;

public interface SRIElectronicDocumentService {

    void generateXML(InvoiceClient electronicDocument) throws ElectronicDocumentException;
    void sign(Signature signature) throws ElectronicDocumentException;
    void reception() throws ElectronicDocumentException;

}
