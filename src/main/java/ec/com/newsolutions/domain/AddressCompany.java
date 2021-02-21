package ec.com.newsolutions.domain;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "address_company")
public class AddressCompany extends AbstractMainEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id", nullable = false)
    private Company company;

    @Column(name = "address", length = 200, nullable = false)
    private String address;

    @Column(name = "phone", length = 13, nullable = false)
    private String phone;

    @Column(name = "mobile_phone", length = 13)
    private String mobilePhone;

    @Column(name = "postal_code", length = 6)
    private String postalCode;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="province_id", nullable = false)
    private Province province;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="canton_id", nullable = false)
    private Canton canton;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="parish_id", nullable = false)
    private Parish parish;

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
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

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public Province getProvince() {
        return province;
    }

    public void setProvince(Province province) {
        this.province = province;
    }

    public Canton getCanton() {
        return canton;
    }

    public void setCanton(Canton canton) {
        this.canton = canton;
    }

    public Parish getParish() {
        return parish;
    }

    public void setParish(Parish parish) {
        this.parish = parish;
    }
}
