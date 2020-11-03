package ec.com.newsolutions.service.dto;

import java.util.ArrayList;
import java.util.List;

public class AuthorityDTO extends AbstractMainDTO {

    private Long organizationId;

    private String name;

    private String description;

    private Boolean active;

    private List<PermitDTO> permits  = new ArrayList<>();

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
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

    public List<PermitDTO> getPermits() {
        return permits;
    }

    public void setPermits(List<PermitDTO> permits) {
        this.permits = permits;
    }
}
