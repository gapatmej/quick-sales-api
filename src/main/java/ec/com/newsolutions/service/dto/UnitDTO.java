package ec.com.newsolutions.service.dto;

import ec.com.newsolutions.domain.enumeration.UnitTypeEnum;

public class UnitDTO extends AbstractMainDTO {

    private Long organizationId;
    private String code;
    private String name;
    private String description;
    private UnitTypeEnum unitType;
    private Boolean predetermined;
    private Boolean active;

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
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

    public UnitTypeEnum getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitTypeEnum unitType) {
        this.unitType = unitType;
    }

    public Boolean getPredetermined() {
        return predetermined;
    }

    public void setPredetermined(Boolean predetermined) {
        this.predetermined = predetermined;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
