package ec.com.newsolutions.domain;


import javax.persistence.*;

import ec.com.newsolutions.domain.enumeration.EmissionTypeEnum;
import ec.com.newsolutions.domain.enumeration.IdentificationTypeEnum;
import ec.com.newsolutions.domain.enumeration.SRIEnvironmentEnum;
import ec.com.newsolutions.domain.enumeration.TokenEnum;

/**
 * A Organization.
 */
@Entity
@Table(name = "organization")
public class Organization extends AbstractMainEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "identification_type", length = 20, nullable = false)
    private IdentificationTypeEnum identificationType;

    @Column(name = "identification", length = 13, unique = true, nullable = false)
    private String identification;

    @Column(name = "business_name", length = 200, nullable = false)
    private String businessName;

    @Column(name = "tradename", length = 200, nullable = false)
    private String tradename;

    @Column(name = "address", length = 200, nullable = false)
    private String address;

    @Column(name = "phone", length = 13)
    private String phone;

    @Column(name = "movil_phone", length = 13)
    private String movilPhone;

    @Column(name = "special_taxpayer_number")
    private Integer specialTaxpayerNumber;

    @Column(name = "keep_accounting", nullable = false)
    private Boolean keepAccounting;

    @Column(name = "logo", length = 200)
    private String logo;

    @Enumerated(EnumType.STRING)
    @Column(name = "token", length = 20, nullable = false)
    private TokenEnum token;

    @Column(name = "mail", length = 100, nullable = false)
    private String mail;

    @Enumerated(EnumType.STRING)
    @Column(name = "sri_environment", length = 20, nullable = false)
    private SRIEnvironmentEnum sriEnvironment;

    @Enumerated(EnumType.STRING)
    @Column(name = "emission_type", length = 10, nullable = false)
    private EmissionTypeEnum emissionType = EmissionTypeEnum.NORMAL;

    public IdentificationTypeEnum getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(IdentificationTypeEnum identificationType) {
        this.identificationType = identificationType;
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
        return keepAccounting;
    }

    public void setKeepAccounting(Boolean keepAccounting) {
        this.keepAccounting = keepAccounting;
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
