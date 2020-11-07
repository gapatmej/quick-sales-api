package ec.com.newsolutions.domain;

import javax.persistence.*;
/**
 * A Cellar.
 */
@Entity
@Table(name = "cellar")
public class Cellar extends AbstractMainEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id", nullable = false)
    private Organization organization;

    @Column(name = "code", length = 20, nullable = false)
    private String code;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "address", length = 200)
    private String address;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
