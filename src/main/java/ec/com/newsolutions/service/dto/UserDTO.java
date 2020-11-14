package ec.com.newsolutions.service.dto;

import ec.com.newsolutions.domain.Authority;
import ec.com.newsolutions.domain.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class UserDTO extends AbstractMainDTO {

    private String identification;

    private String email;

    private String firstName;

    private String lastName;

    private String phone;

    private boolean activated = false;

    private String langKey;

    private String imageUrl;

    private List<AuthorityDTO> authorities = new ArrayList<>();

    private List<OrganizationDTO> organizations = new ArrayList<>();

    private List<BranchOfficeDTO> branchOffices = new ArrayList<>();

    private List<PermitDTO> permits = new ArrayList<>();

    public UserDTO() {
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean isActivated() {
        return activated;
    }

    public void setActivated(boolean activated) {
        this.activated = activated;
    }

    public String getLangKey() {
        return langKey;
    }

    public void setLangKey(String langKey) {
        this.langKey = langKey;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<AuthorityDTO> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<AuthorityDTO> authorities) {
        this.authorities = authorities;
    }

    public List<OrganizationDTO> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(List<OrganizationDTO> organizations) {
        this.organizations = organizations;
    }

    public List<BranchOfficeDTO> getBranchOffices() {
        return branchOffices;
    }

    public void setBranchOffices(List<BranchOfficeDTO> branchOffices) {
        this.branchOffices = branchOffices;
    }

    public List<PermitDTO> getPermits() {
        return permits;
    }

    public void setPermits(List<PermitDTO> permits) {
        this.permits = permits;
    }
}
