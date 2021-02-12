package ec.com.newsolutions.service.dto;

import ec.com.newsolutions.domain.enumeration.EmissionTypeEnum;
import ec.com.newsolutions.domain.enumeration.ReceiptTypeEnum;
import ec.com.newsolutions.domain.enumeration.SRIDocumentStateEnum;
import ec.com.newsolutions.domain.enumeration.SRIEnvironmentEnum;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

public class ElectronicDocumentDTO extends AbstractMainDTO{

    private SRIEnvironmentEnum sriEnvironment;

    private EmissionTypeEnum emissionType ;

    private String accessKey;

    private ReceiptTypeEnum receiptType;

    private SRIDocumentStateEnum sriDocumentState;

    private Instant authorizationDate;

    private Set<SriMessageDTO> sriMessages = new HashSet<>();

    public SRIEnvironmentEnum getSriEnvironment() {
        return sriEnvironment;
    }

    public void setSriEnvironment(SRIEnvironmentEnum sriEnvironment) {
        this.sriEnvironment = sriEnvironment;
    }

    public EmissionTypeEnum getEmissionType() {
        return emissionType;
    }

    public void setEmissionType(EmissionTypeEnum emissionType) {
        this.emissionType = emissionType;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public ReceiptTypeEnum getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(ReceiptTypeEnum receiptType) {
        this.receiptType = receiptType;
    }

    public SRIDocumentStateEnum getSriDocumentState() {
        return sriDocumentState;
    }

    public void setSriDocumentState(SRIDocumentStateEnum sriDocumentState) {
        this.sriDocumentState = sriDocumentState;
    }

    public Instant getAuthorizationDate() {
        return authorizationDate;
    }

    public void setAuthorizationDate(Instant authorizationDate) {
        this.authorizationDate = authorizationDate;
    }

    public Set<SriMessageDTO> getSriMessages() {
        return sriMessages;
    }

    public void setSriMessages(Set<SriMessageDTO> sriMessages) {
        this.sriMessages = sriMessages;
    }
}
