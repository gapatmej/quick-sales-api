package ec.com.newsolutions.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import ec.com.newsolutions.domain.enumeration.IdentificationTypeEnum;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "company")
public class Company extends AbstractMainEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id", nullable = false)
    private Organization organization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_category_id", nullable = false)
    private CompanyCategory companyCategory;

    @Column(name = "business_name", length = 200, nullable = false)
    private String businessName;

    @Column(name = "tradename", length = 200, nullable = false)
    private String tradename;

    @Enumerated(EnumType.STRING)
    @Column(name = "identification_type", nullable = false)
    private IdentificationTypeEnum identificationType;

    @Column(name = "identification", unique = true, nullable = false)
    private String identification;

    @Column(name = "client" )
    private Boolean client = false;

    @NotNull
    @Column(name = "provider")
    private Boolean Provider = false;

    @OneToMany(mappedBy = "company", fetch = FetchType.LAZY)
    private Set<AddressCompany> addressCompanies = new HashSet<>();

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public CompanyCategory getCompanyCategory() {
        return companyCategory;
    }

    public void setCompanyCategory(CompanyCategory companyCategory) {
        this.companyCategory = companyCategory;
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
        return Provider;
    }

    public void setProvider(Boolean provider) {
        Provider = provider;
    }

    public Set<AddressCompany> getAddressCompanies() {
        return addressCompanies;
    }

    public void setAddressCompanies(Set<AddressCompany> addressCompanies) {
        this.addressCompanies = addressCompanies;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
