package ec.com.newsolutions.domain;

import javax.persistence.*;

@Entity
@Table(name = "jhi_permit")
public class Permit extends AbstractMainEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id", nullable = false)
    private Organization organization;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "resource", length = 200)
    private String resource;

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getResource() {
        return resource;
    }

    public void setResource(String resource) {
        this.resource = resource;
    }

}
