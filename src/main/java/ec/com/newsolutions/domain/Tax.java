package ec.com.newsolutions.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import ec.com.newsolutions.domain.enumeration.TaxTypeEnum;

@Entity
@Table(name = "tax")
public class Tax extends AbstractMainEntity {

    @Enumerated(EnumType.STRING)
    @Column(name = "tax_type", length = 10, nullable = false)
    private TaxTypeEnum taxType;

    @Column(name = "code", length = 20, nullable = false, unique = true)
    private String code;

    @Column(name = "name", length = 200, nullable = false)
    private String name;

    @Column(name = "description", length = 200 )
    private String description;

    @Column(name = "percentage", nullable = false)
    private Float percentage;

    @Column(name = "active", nullable = false)
    private Boolean active;

    public TaxTypeEnum getTaxType() {
        return taxType;
    }

    public void setTaxType(TaxTypeEnum taxType) {
        this.taxType = taxType;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Float getPercentage() {
        return percentage;
    }

    public void setPercentage(Float percentage) {
        this.percentage = percentage;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
