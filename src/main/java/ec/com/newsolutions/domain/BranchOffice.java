package ec.com.newsolutions.domain;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "branch_office")
public class BranchOffice extends AbstractMainEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id", nullable = false)
    private Organization organization;

    @Column(name = "business_name", length = 200, nullable = false)
    private String businessName;

    @Column(name = "address", length = 200, nullable = false)
    private String address;

    @Column(name = "phone", length = 13)
    private String phone;

    @Column(name = "movil_phone", length = 13 )
    private String movilPhone;

    @Column(name = "establishment_code", unique = true, length = 3, nullable = false)
    private String establishmentCode;

    @Column(name = "active", nullable = false)
    private Boolean active;

    @OneToMany(mappedBy = "branchOffice", fetch = FetchType.LAZY)
    private Set<EmissionPoint> emissionPoints = new HashSet<>();

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMovilPhone() {
        return movilPhone;
    }

    public void setMovilPhone(String movilPhone) {
        this.movilPhone = movilPhone;
    }

    public String getEstablishmentCode() {
        return establishmentCode;
    }

    public void setEstablishmentCode(String establishmentCode) {
        this.establishmentCode = establishmentCode;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public Set<EmissionPoint> getEmissionPoints() {
        return emissionPoints;
    }

    public void setEmissionPoints(Set<EmissionPoint> emissionPoints) {
        this.emissionPoints = emissionPoints;
    }
}
