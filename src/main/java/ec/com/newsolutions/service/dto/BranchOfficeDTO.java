package ec.com.newsolutions.service.dto;

import java.util.ArrayList;
import java.util.List;

public class BranchOfficeDTO extends AbstractMainDTO {

    private Long organizationId;

    private String businessName;

    private String address;

    private String phone;

    private String mobilePhone;

    private String establishmentCode;

    private Boolean active;

    private List<EmissionPointDTO> emissionPoints = new ArrayList<>();

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public String getEstablishmentCode() {
        return establishmentCode;
    }

    public void setEstablishmentCode(String establishmentCode) {
        this.establishmentCode = establishmentCode;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    public List<EmissionPointDTO> getEmissionPoints() {
        return emissionPoints;
    }

    public void setEmissionPoints(List<EmissionPointDTO> emissionPoints) {
        this.emissionPoints = emissionPoints;
    }
}
