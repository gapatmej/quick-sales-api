package ec.com.newsolutions.service.dto;

import java.io.Serializable;

public class WorkspaceDTO implements Serializable {

    private Long organizationId;

    private Long branchOfficeId;

    private Long emissionPointId;


    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getBranchOfficeId() {
        return branchOfficeId;
    }

    public void setBranchOfficeId(Long branchOfficeId) {
        this.branchOfficeId = branchOfficeId;
    }

    public Long getEmissionPointId() {
        return emissionPointId;
    }

    public void setEmissionPointId(Long emissionPointId) {
        this.emissionPointId = emissionPointId;
    }
}
