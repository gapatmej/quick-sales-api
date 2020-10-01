package ec.com.newsolutions.service.dto;

import ec.com.newsolutions.domain.enumeration.EmissionTypeEnum;
import ec.com.newsolutions.domain.enumeration.IdentificationTypeEnum;
import ec.com.newsolutions.domain.enumeration.SRIEnvironmentEnum;
import ec.com.newsolutions.domain.enumeration.TokenEnum;

public class OrganizationDTO extends  AbstractMainDTO{

    private IdentificationTypeEnum identificationType;

    private String identification;

    private String businessName;

    private String tradename;

    private String address;

    private String phone;

    private String movilPhone;

    private Integer specialTaxpayerNumber;

    private Boolean keepAccounting;

    private String logo;

    private TokenEnum token;

    private String mail;

    private SRIEnvironmentEnum sriEnvironment;

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
