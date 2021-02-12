package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.ElectronicDocument;
import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.domain.TributaryDocument;
import ec.com.newsolutions.domain.enumeration.SRIDocumentStateEnum;

public interface ElectronicDocumentService extends AbstractService<ElectronicDocument> {
    void build(TributaryDocument tributaryDocument);
    void updateSriDocumentState(SRIDocumentStateEnum sriDocumentStateEnum, ElectronicDocument electronicDocument);
}
