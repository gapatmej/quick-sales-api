package ec.com.newsolutions.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import ec.com.newsolutions.domain.enumeration.EmissionTypeEnum;
import ec.com.newsolutions.domain.enumeration.SRIEnvironmentEnum;
import ec.com.newsolutions.domain.enumeration.TokenEnum;

/**
 * A Organization.
 */
@Entity
@Table(name = "organization")
public class Organization extends AbstractAuditingEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "identification", nullable = false)
    private String identification;

    @NotNull
    @Column(name = "business_name", nullable = false)
    private String businessName;

    @NotNull
    @Column(name = "tradename", nullable = false)
    private String tradename;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "movil_phone")
    private String movilPhone;

    @Max(value = 99999)
    @Column(name = "special_taxpayer_number")
    private Integer specialTaxpayerNumber;

    @NotNull
    @Column(name = "is_keep_accounting", nullable = false)
    private Boolean isKeepAccounting;

    @Column(name = "logo")
    private String logo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "token", nullable = false)
    private TokenEnum token;

    @NotNull
    @Column(name = "mail", nullable = false)
    private String mail;

    @Enumerated(EnumType.STRING)
    @Column(name = "sri_environment", nullable = false)
    private SRIEnvironmentEnum sriEnvironment;

    @Enumerated(EnumType.STRING)
    @Column(name = "emission_type", nullable = false)
    private EmissionTypeEnum emissionType = EmissionTypeEnum.NORMAL;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMovilPhone() {
        return movilPhone;
    }

    public void setMovilPhone(String movilPhone) {
        this.movilPhone = movilPhone;
    }

    public Integer getSpecialTaxpayerNumber() {
        return specialTaxpayerNumber;
    }

    public void setSpecialTaxpayerNumber(Integer specialTaxpayerNumber) {
        this.specialTaxpayerNumber = specialTaxpayerNumber;
    }

    public Boolean getKeepAccounting() {
        return isKeepAccounting;
    }

    public void setKeepAccounting(Boolean keepAccounting) {
        isKeepAccounting = keepAccounting;
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public TokenEnum getToken() {
        return token;
    }

    public void setToken(TokenEnum token) {
        this.token = token;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
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
}
