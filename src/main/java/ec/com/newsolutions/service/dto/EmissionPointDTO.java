package ec.com.newsolutions.service.dto;

import ec.com.newsolutions.domain.BranchOffice;

public class EmissionPointDTO extends AbstractMainDTO{

    private String name ;

    private String emissionPointCode;

    private BranchOffice branchOffice;

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
