package ec.com.newsolutions.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

/**
 * A BranchOffice.
 */
@Entity
@Table(name = "branch_office")
public class BranchOffice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "business_name", nullable = false)
    private String businessName;

    @NotNull
    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "phone")
    private String phone;

    @Column(name = "movil_phone")
    private String movilPhone;

    @Max(value = 999)
    @Column(name = "establishment_code")
    private Integer establishmentCode;

    @Max(value = 999)
    @Column(name = "emission_point_code")
    private Integer emissionPointCode;

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

    public BranchOffice businessName(String businessName) {
        this.businessName = businessName;
        return this;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAddress() {
        return address;
    }

    public BranchOffice address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public BranchOffice phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMovilPhone() {
        return movilPhone;
    }

    public BranchOffice movilPhone(String movilPhone) {
        this.movilPhone = movilPhone;
        return this;
    }

    public void setMovilPhone(String movilPhone) {
        this.movilPhone = movilPhone;
    }

    public Integer getEstablishmentCode() {
        return establishmentCode;
    }

    public BranchOffice establishmentCode(Integer establishmentCode) {
        this.establishmentCode = establishmentCode;
        return this;
    }

    public void setEstablishmentCode(Integer establishmentCode) {
        this.establishmentCode = establishmentCode;
    }

    public Integer getEmissionPointCode() {
        return emissionPointCode;
    }

    public BranchOffice emissionPointCode(Integer emissionPointCode) {
        this.emissionPointCode = emissionPointCode;
        return this;
    }

    public void setEmissionPointCode(Integer emissionPointCode) {
        this.emissionPointCode = emissionPointCode;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof BranchOffice)) {
            return false;
        }
        return id != null && id.equals(((BranchOffice) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "BranchOffice{" +
            "id=" + getId() +
            ", businessName='" + getBusinessName() + "'" +
            ", address='" + getAddress() + "'" +
            ", phone='" + getPhone() + "'" +
            ", movilPhone='" + getMovilPhone() + "'" +
            ", establishmentCode=" + getEstablishmentCode() +
            ", emissionPointCode=" + getEmissionPointCode() +
            "}";
    }
}
