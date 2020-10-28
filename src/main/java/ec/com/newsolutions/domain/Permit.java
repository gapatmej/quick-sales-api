package ec.com.newsolutions.domain;

import javax.persistence.*;

@Entity
@Table(name = "jhi_permit")
public class Permit extends AbstractMainEntity {

    @Column(length = 50)
    private String name;

    @Column(name = "resource", length = 200)
    private String resource;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="authority_id", nullable = false)
    private Authority authority;

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

    public Authority getAuthority() {
        return authority;
    }

    public void setAuthority(Authority authority) {
        this.authority = authority;
    }
}
