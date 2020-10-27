package ec.com.newsolutions.service.dto;

import java.io.Serializable;

public class WorkspaceDTO implements Serializable {

    private Long organizationId;

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }
}
