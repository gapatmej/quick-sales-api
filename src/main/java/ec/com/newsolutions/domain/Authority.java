package ec.com.newsolutions.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * An authority (a security role) used by Spring Security.
 */
@Entity
@Table(name = "jhi_authority")
public class Authority extends AbstractMainEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id", nullable = false)
    private Organization organization;

    @Column(length = 50, nullable = false)
    private String name;

    @Column(name = "description", length = 200)
    private String description;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "jhi_authority_permit",
        joinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "permit_id", referencedColumnName = "id")})
    private Set<Permit> permits = new HashSet<>();

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<Permit> getPermits() {
        return permits;
    }

    public void setPermits(Set<Permit> permits) {
        this.permits = permits;
    }
}
