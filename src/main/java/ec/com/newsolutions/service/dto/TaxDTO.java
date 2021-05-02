package ec.com.newsolutions.service.dto;

import ec.com.newsolutions.domain.enumeration.TaxTypeEnum;

public class TaxDTO extends AbstractMainDTO {

    private Long organizationId;

    private TaxTypeEnum taxType;

    private Integer code;

    private String name;

    private String description;

    private Float percentage;

    private Boolean active;

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public TaxTypeEnum getTaxType() {
        return taxType;
    }

    public void setTaxType(TaxTypeEnum taxType) {
        this.taxType = taxType;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
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
