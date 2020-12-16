package ec.com.newsolutions.service.dto;
import ec.com.newsolutions.domain.enumeration.IdentificationTypeEnum;

import java.util.ArrayList;
import java.util.List;

public class CompanyDTO extends AbstractMainDTO{

    private Long organizationId;

    private Long companyCategoryId;

    private String businessName;

    private String tradename;

    private IdentificationTypeEnum identificationType;

    private String identification;

    private Boolean client = false;

    private Boolean provider = false;

    private List<AddressCompanyDTO> addressCompanies = new ArrayList<>();

    private String email;

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getCompanyCategoryId() {
        return companyCategoryId;
    }

    public void setCompanyCategoryId(Long companyCategoryId) {
        this.companyCategoryId = companyCategoryId;
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

    public Boolean getClient() {
        return client;
    }

    public void setClient(Boolean client) {
        this.client = client;
    }

    public Boolean getProvider() {
        return provider;
    }

    public void setProvider(Boolean provider) {
        this.provider = provider;
    }

    public List<AddressCompanyDTO> getAddressCompanies() {
        return addressCompanies;
    }

    public void setAddressCompanies(List<AddressCompanyDTO> addressCompanies) {
        this.addressCompanies = addressCompanies;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
