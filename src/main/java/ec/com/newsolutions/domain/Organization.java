package ec.com.newsolutions.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import ec.com.newsolutions.domain.enumeration.TokenEnum;

/**
 * A Organization.
 */
@Entity
@Table(name = "organization")
public class Organization implements Serializable {

    private static final long serialVersionUID = 1L;

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

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getIdentification() {
        return identification;
    }

    public Organization identification(String identification) {
        this.identification = identification;
        return this;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getBusinessName() {
        return businessName;
    }

    public Organization businessName(String businessName) {
        this.businessName = businessName;
        return this;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getTradename() {
        return tradename;
    }

    public Organization tradename(String tradename) {
        this.tradename = tradename;
        return this;
    }

    public void setTradename(String tradename) {
        this.tradename = tradename;
    }

    public String getAddress() {
        return address;
    }

    public Organization address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public Organization phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMovilPhone() {
        return movilPhone;
    }

    public Organization movilPhone(String movilPhone) {
        this.movilPhone = movilPhone;
        return this;
    }

    public void setMovilPhone(String movilPhone) {
        this.movilPhone = movilPhone;
    }

    public Integer getSpecialTaxpayerNumber() {
        return specialTaxpayerNumber;
    }

    public Organization specialTaxpayerNumber(Integer specialTaxpayerNumber) {
        this.specialTaxpayerNumber = specialTaxpayerNumber;
        return this;
    }

    public void setSpecialTaxpayerNumber(Integer specialTaxpayerNumber) {
        this.specialTaxpayerNumber = specialTaxpayerNumber;
    }

    public Boolean isIsKeepAccounting() {
        return isKeepAccounting;
    }

    public Organization isKeepAccounting(Boolean isKeepAccounting) {
        this.isKeepAccounting = isKeepAccounting;
        return this;
    }

    public void setIsKeepAccounting(Boolean isKeepAccounting) {
        this.isKeepAccounting = isKeepAccounting;
    }

    public String getLogo() {
        return logo;
    }

    public Organization logo(String logo) {
        this.logo = logo;
        return this;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    public TokenEnum getToken() {
        return token;
    }

    public Organization token(TokenEnum token) {
        this.token = token;
        return this;
    }

    public void setToken(TokenEnum token) {
        this.token = token;
    }

    public String getMail() {
        return mail;
    }

    public Organization mail(String mail) {
        this.mail = mail;
        return this;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Organization)) {
            return false;
        }
        return id != null && id.equals(((Organization) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Organization{" +
            "id=" + getId() +
            ", identification='" + getIdentification() + "'" +
            ", businessName='" + getBusinessName() + "'" +
            ", tradename='" + getTradename() + "'" +
            ", address='" + getAddress() + "'" +
            ", phone='" + getPhone() + "'" +
            ", movilPhone='" + getMovilPhone() + "'" +
            ", specialTaxpayerNumber=" + getSpecialTaxpayerNumber() +
            ", isKeepAccounting='" + isIsKeepAccounting() + "'" +
            ", logo='" + getLogo() + "'" +
            ", token='" + getToken() + "'" +
            ", mail='" + getMail() + "'" +
            "}";
    }
}
