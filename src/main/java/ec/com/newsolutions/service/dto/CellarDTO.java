package ec.com.newsolutions.service.dto;

public class CellarDTO extends AbstractMainDTO {

    private Long organizationId;
    private String code;
    private String name;
    private String address;
    private String description;
    private Boolean predetermined;
    private Boolean active;

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getPredetermined() {
        return predetermined;
    }

    public void setPredetermined(Boolean predetermined) {
        this.predetermined = predetermined;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
