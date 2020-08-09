package ec.com.newsolutions.domain;

import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "emission_point")
public class EmissionPoint extends AbstractAuditingEntity  implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(length = 20, name = "name", nullable = false)
    private String name ;

    @NotNull
    @Size(min = 3, max = 3)
    @Column(name = "emission_point_code", nullable = false)
    private String emissionPointCode;

    @NotNull
    @Size(min = 9, max = 9)
    @Column(name = "sequence", nullable = false)
    private String sequence;

    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name="branch_office_id", nullable = false)
    private BranchOffice branchOffice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public BranchOffice getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(BranchOffice branchOffice) {
        this.branchOffice = branchOffice;
    }
}
