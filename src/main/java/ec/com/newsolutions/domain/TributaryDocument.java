package ec.com.newsolutions.domain;

import ec.com.newsolutions.domain.enumeration.IdentificationTypeEnum;

import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import javax.persistence.OneToOne;
import java.time.Instant;

@MappedSuperclass
public class TributaryDocument extends AbstractMainEntity {

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="electronic_document_id", nullable = false)
    private ElectronicDocument electronicDocument;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="document_id", nullable = false)
    private Document document;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id", nullable = false)
    private Organization organization;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="branch_office_id", nullable = false)
    private BranchOffice branchOffice;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id", nullable = false)
    private Company company;

    @Column(name = "business_name", length = 200, nullable = false)
    private String businessName;

    @Enumerated(EnumType.STRING)
    @Column(name = "identification_type", length = 20, nullable = false)
    private IdentificationTypeEnum identificationType;

    @Column(name = "identification", length = 13, nullable = false)
    private String identification;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="emission_point_id", nullable = false)
    private EmissionPoint emissionPoint;

    @Column(name = "establishment_code", length = 3, nullable = false)
    private String establishmentCode;

    @Column(name = "emission_point_code", length = 3, nullable = false)
    private String emissionPointCode;

    @Column(name = "sequence", nullable = false)
    private int sequence;

    @Column(name = "date_issue", nullable = false)
    private Instant dateIssue;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="address_company_id", nullable = false)
    private AddressCompany addressCompany;

    @Column(name = "address", length = 200, nullable = false)
    private String address;

    @Column(name = "phone", length = 13)
    private String phone;

    @Column(name = "email", length = 200, nullable = false)
    private String email;

    public ElectronicDocument getElectronicDocument() {
        return electronicDocument;
    }

    public void setElectronicDocument(ElectronicDocument electronicDocument) {
        this.electronicDocument = electronicDocument;
    }

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public BranchOffice getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(BranchOffice branchOffice) {
        this.branchOffice = branchOffice;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public IdentificationTypeEnum getIdentificationType() {
        return identificationType;
    }

    public void setIdentificationType(IdentificationTypeEnum identificationType) {
        this.identificationType = identificationType;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public EmissionPoint getEmissionPoint() {
        return emissionPoint;
    }

    public void setEmissionPoint(EmissionPoint emissionPoint) {
        this.emissionPoint = emissionPoint;
    }

    public String getEstablishmentCode() {
        return establishmentCode;
    }

    public void setEstablishmentCode(String establishmentCode) {
        this.establishmentCode = establishmentCode;
    }

    public String getEmissionPointCode() {
        return emissionPointCode;
    }

    public void setEmissionPointCode(String emissionPointCode) {
        this.emissionPointCode = emissionPointCode;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public Instant getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(Instant dateIssue) {
        this.dateIssue = dateIssue;
    }

    public AddressCompany getAddressCompany() {
        return addressCompany;
    }

    public void setAddressCompany(AddressCompany addressCompany) {
        this.addressCompany = addressCompany;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
