package ec.com.newsolutions.domain;

import ec.com.newsolutions.config.Constants;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/**
 * A user.
 */
@Entity
@Table(name = "jhi_user")
@NamedEntityGraph(
    name = "user-account",
    attributeNodes = {
        @NamedAttributeNode(value = "authorities", subgraph = "permits-subgraph"),
        @NamedAttributeNode("organizations"),
        @NamedAttributeNode("branchOffices"),
    },
    subgraphs = {
        @NamedSubgraph(
            name = "permits-subgraph",
            attributeNodes = {
                @NamedAttributeNode("permits")
            }
        )
    }
)
public class User extends AbstractMainEntity implements Serializable {

    @Column(name = "identification", length = 13, unique = true, nullable = false)
    private String identification;

    @Column(length = 100, unique = true, nullable = false)
    private String email;

    @JsonIgnore
    @Column(name = "password_hash", length = 60, nullable = false)
    private String password;

    @Column(name = "first_name", length = 50)
    private String firstName;

    @Column(name = "last_name", length = 50)
    private String lastName;

    @Column(name = "phone", length = 13)
    private String phone;

    @Column(nullable = false)
    private boolean activated = false;

    @Column(name = "lang_key", length = 10)
    private String langKey;

    @Column(name = "image_url", length = 200)
    private String imageUrl;

    @Column(name = "activation_key", length = 20)
    @JsonIgnore
    private String activationKey;

    @Column(name = "reset_key", length = 20)
    @JsonIgnore
    private String resetKey;

    @Column(name = "reset_date")
    private Instant resetDate = null;

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "jhi_user_authority",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "authority_id", referencedColumnName = "id")})
    private Set<Authority> authorities = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "jhi_user_organization",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "organization_id", referencedColumnName = "id")})
    private Set<Organization> organizations = new HashSet<>();

    @JsonIgnore
    @ManyToMany
    @JoinTable(
        name = "jhi_user_branch_office",
        joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
        inverseJoinColumns = {@JoinColumn(name = "branch_office_id", referencedColumnName = "id")})
    private Set<BranchOffice> branchOffices = new HashSet<>();

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getActivationKey() {
        return activationKey;
    }

    public void setActivationKey(String activationKey) {
        this.activationKey = activationKey;
    }

    public String getResetKey() {
        return resetKey;
    }

    public void setResetKey(String resetKey) {
        this.resetKey = resetKey;
    }

    public Instant getResetDate() {
        return resetDate;
    }

    public void setResetDate(Instant resetDate) {
        this.resetDate = resetDate;
    }

    public Set<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<Authority> authorities) {
        this.authorities = authorities;
    }

    public Set<Organization> getOrganizations() {
        return organizations;
    }

    public void setOrganizations(Set<Organization> organizations) {
        this.organizations = organizations;
    }

    public Set<BranchOffice> getBranchOffices() {
        return branchOffices;
    }

    public void setBranchOffices(Set<BranchOffice> branchOffices) {
        this.branchOffices = branchOffices;
    }
}
