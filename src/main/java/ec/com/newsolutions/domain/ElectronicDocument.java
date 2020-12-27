package ec.com.newsolutions.domain;

import ec.com.newsolutions.domain.enumeration.EmissionTypeEnum;
import ec.com.newsolutions.domain.enumeration.ReceiptTypeEnum;
import ec.com.newsolutions.domain.enumeration.SRIEnvironmentEnum;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.time.Instant;

@MappedSuperclass
public class ElectronicDocument extends AbstractMainEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id", nullable = false)
    private Organization organization;

    @Enumerated(EnumType.STRING)
    @Column(name = "sri_environment", length = 20, nullable = false)
    private SRIEnvironmentEnum sriEnvironment;

    @Enumerated(EnumType.STRING)
    @Column(name = "emission_type",length = 20, nullable = false)
    private EmissionTypeEnum emissionType ;

    @Column(name = "establishment_code", length = 3, nullable = false)
    private String establishmentCode;

    @Column(name = "emission_point_code", length = 3, nullable = false)
    private String emissionPointCode;

    @Column(name = "sequence", nullable = false)
    private int sequence;

    @Column(name = "date_issue", nullable = false)
    private Instant dateIssue;

    @Column(name = "accessKey", nullable = false, unique = true)
    private String accessKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "receipt_type",length = 20, nullable = false)
    private ReceiptTypeEnum receiptType;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

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

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public Instant getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(Instant dateIssue) {
        this.dateIssue = dateIssue;
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
}
