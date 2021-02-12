package ec.com.newsolutions.domain;

import ec.com.newsolutions.domain.enumeration.EmissionTypeEnum;
import ec.com.newsolutions.domain.enumeration.ReceiptTypeEnum;
import ec.com.newsolutions.domain.enumeration.SRIDocumentStateEnum;
import ec.com.newsolutions.domain.enumeration.SRIEnvironmentEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "electronic_document")
public class ElectronicDocument extends AbstractMainEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "sri_environment", length = 20, nullable = false)
    private SRIEnvironmentEnum sriEnvironment;

    @Enumerated(EnumType.STRING)
    @Column(name = "emission_type",length = 20, nullable = false)
    private EmissionTypeEnum emissionType ;

    @Column(name = "accessKey", nullable = false, unique = true)
    private String accessKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "receipt_type",length = 20, nullable = false)
    private ReceiptTypeEnum receiptType;

    @Enumerated(EnumType.STRING)
    @Column(name = "sri_document_state", length = 20, nullable = false)
    private SRIDocumentStateEnum sriDocumentState;

    @Column(name = "authorization_date")
    private Instant authorizationDate;

    @OneToMany(mappedBy = "electronicDocument", fetch = FetchType.LAZY)
    private Set<SriMessage> sriMessages = new HashSet<>();

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

    public Set<SriMessage> getSriMessages() {
        return sriMessages;
    }

    public void setSriMessages(Set<SriMessage> sriMessages) {
        this.sriMessages = sriMessages;
    }
}
