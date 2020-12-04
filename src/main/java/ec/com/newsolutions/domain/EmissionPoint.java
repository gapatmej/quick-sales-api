package ec.com.newsolutions.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;

@Entity
@Table(name = "emission_point", uniqueConstraints =  @UniqueConstraint(columnNames = {"branch_office_id", "emission_point_code"}))
public class EmissionPoint extends AbstractMainEntity {

    @Column(name = "emission_point_code", length = 3, nullable = false)
    private String emissionPointCode;

    @Column(name = "name", length = 100, nullable = false)
    private String name ;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="branch_office_id", nullable = false)
    private BranchOffice branchOffice;

    @Column(name = "active", nullable = false)
    private Boolean active;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmissionPointCode() {
        return emissionPointCode;
    }

    public void setEmissionPointCode(String emissionPointCode) {
        this.emissionPointCode = emissionPointCode;
    }

    public BranchOffice getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(BranchOffice branchOffice) {
        this.branchOffice = branchOffice;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
