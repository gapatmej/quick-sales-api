package ec.com.newsolutions.service.dto;

import java.io.Serializable;

public class WorkspaceDTO implements Serializable {

    private OrganizationDTO organization;

    private BranchOfficeDTO branchOffice;

    private EmissionPointDTO emissionPoint;

    public OrganizationDTO getOrganization() {
        return organization;
    }

    public void setOrganization(OrganizationDTO organization) {
        this.organization = organization;
    }

    public BranchOfficeDTO getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(BranchOfficeDTO branchOffice) {
        this.branchOffice = branchOffice;
    }

    public EmissionPointDTO getEmissionPoint() {
        return emissionPoint;
    }

    public void setEmissionPoint(EmissionPointDTO emissionPoint) {
        this.emissionPoint = emissionPoint;
    }
}
