package ec.com.newsolutions.service.dto;

import ec.com.newsolutions.domain.enumeration.TaxTypeEnum;

public class TaxDTO extends AbstractMainDTO {

    private TaxTypeEnum taxType;

    private String code;

    private String name;

    private String description;

    private Float percentage;

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
