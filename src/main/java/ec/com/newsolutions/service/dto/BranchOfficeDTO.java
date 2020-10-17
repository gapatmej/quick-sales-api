package ec.com.newsolutions.service.dto;

import java.util.HashSet;
import java.util.Set;

public class BranchOfficeDTO extends AbstractMainDTO {

    private String businessName;

    private String address;

    private String phone;

    private String movilPhone;

    private String establishmentCode;

    private Boolean active;

    private Set<EmissionPointDTO> emissionPoints = new HashSet<>();

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

    public String getMovilPhone() {
        return movilPhone;
    }

    public void setMovilPhone(String movilPhone) {
        this.movilPhone = movilPhone;
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

    public Set<EmissionPointDTO> getEmissionPoints() {
        return emissionPoints;
    }

    public void setEmissionPoints(Set<EmissionPointDTO> emissionPoints) {
        this.emissionPoints = emissionPoints;
    }
}
