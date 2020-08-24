package ec.com.newsolutions.domain;

import ec.com.newsolutions.domain.enumeration.ReceiptTypeEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.Instant;

@MappedSuperclass
public class ElectronicDocument extends AbstractAuditingEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id", nullable = false)
    private Organization organization;

    @NotNull
    @Column(name = "establishment_code", nullable = false)
    private String establishmentCode;

    @NotNull
    @Column(name = "emission_point_code", nullable = false)
    private String emissionPointCode;

    @NotNull
    @Column(name = "sequence", nullable = false)
    private String sequence;

    @NotNull
    @Column(name = "date_issue", nullable = false)
    private Instant dateIssue;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="electronic_document_id", unique = true)
    private ElectronicDocumentInfo electronicDocumentInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "receipt_type", nullable = false)
    private ReceiptTypeEnum receiptType;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getEstablishmentCode() {
        return establishmentCode;
    }

    public void setEstablishmentCode(String establishmentCode) {
        this.establishmentCode = establishmentCode;
    }

    public String getEmissionPointCode() {
        return emissionPointCode;
    }

    public void setEmissionPointCode(String emissionPointCode) {
        this.emissionPointCode = emissionPointCode;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Instant getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(Instant dateIssue) {
        this.dateIssue = dateIssue;
    }

    public ElectronicDocumentInfo getElectronicDocumentInfo() {
        return electronicDocumentInfo;
    }

    public void setElectronicDocumentInfo(ElectronicDocumentInfo electronicDocumentInfo) {
        this.electronicDocumentInfo = electronicDocumentInfo;
    }

    public ReceiptTypeEnum getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(ReceiptTypeEnum receiptType) {
        this.receiptType = receiptType;
    }
}
