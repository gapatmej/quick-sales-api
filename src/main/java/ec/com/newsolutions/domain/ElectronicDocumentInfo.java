package ec.com.newsolutions.domain;

import ec.com.newsolutions.domain.enumeration.EmissionTypeEnum;
import ec.com.newsolutions.domain.enumeration.SRIEnvironmentEnum;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "electronic_document_info")
public class ElectronicDocumentInfo extends AbstractAuditingEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "sri_environment", nullable = false)
    private SRIEnvironmentEnum sriEnvironment;

    @Enumerated(EnumType.STRING)
    @Column(name = "emission_type", nullable = false)
    private EmissionTypeEnum emissionType ;

    @Column(name = "business_name", nullable = false)
    private String businessName;

    @Column(name = "tradename", nullable = false)
    private String tradename;

    @Column(name = "identification", nullable = false)
    private String identification;

    @Column(name = "accessKey", nullable = false, unique = true)
    private String accessKey;

    @Column(name = "address", nullable = false)
    private String mainAddress;

    @NotNull
    @Column(name = "establishment_address")
    private String establishmentAddress;

    @Column(name = "special_taxpayer_number")
    private String specialTaxpayerNumber;

    @NotNull
    @Column(name = "is_keep_accounting", nullable = false)
    private Boolean isKeepAccounting;

    public ElectronicDocumentInfo(Organization organization) {
        this.sriEnvironment = organization.getSriEnvironment();
        this.emissionType = organization.getEmissionType();
        this.businessName = organization.getBusinessName();
        this.tradename = organization.getTradename();
        this.identification = organization.getIdentification();
        this.mainAddress = organization.getAddress();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getTradename() {
        return tradename;
    }

    public void setTradename(String tradename) {
        this.tradename = tradename;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getMainAddress() {
        return mainAddress;
    }

    public void setMainAddress(String mainAddress) {
        this.mainAddress = mainAddress;
    }

    public String getEstablishmentAddress() {
        return establishmentAddress;
    }

    public void setEstablishmentAddress(String establishmentAddress) {
        this.establishmentAddress = establishmentAddress;
    }

    public String getSpecialTaxpayerNumber() {
        return specialTaxpayerNumber;
    }

    public void setSpecialTaxpayerNumber(String specialTaxpayerNumber) {
        this.specialTaxpayerNumber = specialTaxpayerNumber;
    }

    public Boolean isKeepAccounting() {
        return isKeepAccounting;
    }

    public void setKeepAccounting(Boolean keepAccounting) {
        isKeepAccounting = keepAccounting;
    }
}
