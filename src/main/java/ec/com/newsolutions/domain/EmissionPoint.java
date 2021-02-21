package ec.com.newsolutions.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

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

}
