package ec.com.newsolutions.service.dto;

import ec.com.newsolutions.domain.enumeration.PayWaySRIEnum;

public class PayWayDTO extends AbstractMainDTO {

    private Long organizationId;
    private String code;
    private String name;
    private String description;
    private PayWaySRIEnum payWaySRI;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PayWaySRIEnum getPayWaySRI() {
        return payWaySRI;
    }

    public void setPayWaySRI(PayWaySRIEnum payWaySRI) {
        this.payWaySRI = payWaySRI;
    }
}
