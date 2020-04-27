package ec.com.newsolutions.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import ec.com.newsolutions.domain.enumeration.IdentificationTypeEnum;

/**
 * A Company.
 */
@Entity
@Table(name = "company")
public class Company implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "business_name", nullable = false)
    private String businessName;

    @Column(name = "tradename")
    private String tradename;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "identification_type", nullable = false)
    private IdentificationTypeEnum identificationType;

    @NotNull
    @Column(name = "is_client", nullable = false)
    private Boolean isClient;

    @NotNull
    @Column(name = "is_provider", nullable = false)
    private Boolean isProvider;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "movil_phone")
    private String movilPhone;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBusinessName() {
        return businessName;
    }

    public Company businessName(String businessName) {
        this.businessName = businessName;
        return this;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getTradename() {
        return tradename;
    }

    public Company tradename(String tradename) {
        this.tradename = tradename;
        return this;
    }

    public void setTradename(String tradename) {
        this.tradename = tradename;
    }

    public IdentificationTypeEnum getIdentificationType() {
        return identificationType;
    }

    public Company identificationType(IdentificationTypeEnum identificationType) {
        this.identificationType = identificationType;
        return this;
    }

    public void setIdentificationType(IdentificationTypeEnum identificationType) {
        this.identificationType = identificationType;
    }

    public Boolean isIsClient() {
        return isClient;
    }

    public Company isClient(Boolean isClient) {
        this.isClient = isClient;
        return this;
    }

    public void setIsClient(Boolean isClient) {
        this.isClient = isClient;
    }

    public Boolean isIsProvider() {
        return isProvider;
    }

    public Company isProvider(Boolean isProvider) {
        this.isProvider = isProvider;
        return this;
    }

    public void setIsProvider(Boolean isProvider) {
        this.isProvider = isProvider;
    }

    public String getAddress() {
        return address;
    }

    public Company address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public Company phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMovilPhone() {
        return movilPhone;
    }

    public Company movilPhone(String movilPhone) {
        this.movilPhone = movilPhone;
        return this;
    }

    public void setMovilPhone(String movilPhone) {
        this.movilPhone = movilPhone;
    }

    public String getEmail() {
        return email;
    }

    public Company email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Company)) {
            return false;
        }
        return id != null && id.equals(((Company) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Company{" +
            "id=" + getId() +
            ", businessName='" + getBusinessName() + "'" +
            ", tradename='" + getTradename() + "'" +
            ", identificationType='" + getIdentificationType() + "'" +
            ", isClient='" + isIsClient() + "'" +
            ", isProvider='" + isIsProvider() + "'" +
            ", address='" + getAddress() + "'" +
            ", phone='" + getPhone() + "'" +
            ", movilPhone='" + getMovilPhone() + "'" +
            ", email='" + getEmail() + "'" +
            "}";
    }
}
