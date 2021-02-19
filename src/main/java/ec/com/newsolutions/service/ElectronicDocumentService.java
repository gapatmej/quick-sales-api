package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.ElectronicDocument;
import ec.com.newsolutions.domain.TributaryDocument;
import ec.com.newsolutions.domain.enumeration.SRIDocumentStateEnum;

import java.time.Instant;

public interface ElectronicDocumentService extends AbstractService<ElectronicDocument> {
    void build(TributaryDocument tributaryDocument);
    void updateSriDocumentState(SRIDocumentStateEnum sriDocumentStateEnum, ElectronicDocument electronicDocument);
    void updateSriAuthorizedFields(ElectronicDocument electronicDocument, SRIDocumentStateEnum sriDocumentStateEnum, Instant authorizationDate);
}
