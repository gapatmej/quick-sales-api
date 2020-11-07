package ec.com.newsolutions.domain;

import ec.com.newsolutions.domain.enumeration.UnitTypeEnum;

import javax.persistence.*;

@Entity
@Table(name = "unit")
public class Unit extends AbstractMainEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id", nullable = false)
    private Organization organization;

    @Column(name = "code", length = 20, unique = true, nullable = false)
    private String code;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(name = "unit_type", nullable = false)
    private UnitTypeEnum unitType;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "predetermined", nullable = false)
    private Boolean predetermined;

    @Column(name = "active", nullable = false)
    private Boolean active;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
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

    public UnitTypeEnum getUnitType() {
        return unitType;
    }

    public void setUnitType(UnitTypeEnum unitType) {
        this.unitType = unitType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
