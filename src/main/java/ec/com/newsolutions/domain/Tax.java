package ec.com.newsolutions.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import ec.com.newsolutions.domain.enumeration.TaxTypeEnum;

/**
 * A Tax.
 */
@Entity
@Table(name = "tax")
public class Tax implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false, unique = true)
    private String code;

    @NotNull
    @Column(name = "description", nullable = false)
    private String description;

    @NotNull
    @Column(name = "percentage", nullable = false)
    private Float percentage;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "tax_type", nullable = false)
    private TaxTypeEnum taxType;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public Tax code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public Tax description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPercentage() {
        return percentage;
    }

    public Tax percentage(Float percentage) {
        this.percentage = percentage;
        return this;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public TaxTypeEnum getTaxType() {
        return taxType;
    }

    public Tax taxType(TaxTypeEnum taxType) {
        this.taxType = taxType;
        return this;
    }

    public void setTaxType(TaxTypeEnum taxType) {
        this.taxType = taxType;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Tax)) {
            return false;
        }
        return id != null && id.equals(((Tax) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Tax{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", percentage=" + getPercentage() +
            ", taxType='" + getTaxType() + "'" +
            "}";
    }
}
