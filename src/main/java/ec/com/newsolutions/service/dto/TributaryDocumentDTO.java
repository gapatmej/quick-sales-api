package ec.com.newsolutions.service.dto;

import ec.com.newsolutions.domain.enumeration.IdentificationTypeEnum;

import java.time.Instant;

public class TributaryDocumentDTO extends AbstractMainDTO {

    private Long electronicDocumentId;

    private Long documentId;

    private Long organizationId;

    private Long branchOfficeId;

    private Long companyId;

    private String businessName;

    private IdentificationTypeEnum identificationType;

    private String identification;

    private Long emissionPointId;

    private String establishmentCode;

    private String emissionPointCode;

    private int sequence;

    private Instant dateIssue;

    private Long addressCompanyId;

    private String address;

    private String phone;

    private String email;

    public Long getElectronicDocumentId() {
        return electronicDocumentId;
    }

    public void setElectronicDocumentId(Long electronicDocumentId) {
        this.electronicDocumentId = electronicDocumentId;
    }

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Long getBranchOfficeId() {
        return branchOfficeId;
    }

    public void setBranchOfficeId(Long branchOfficeId) {
        this.branchOfficeId = branchOfficeId;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
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

    public Long getEmissionPointId() {
        return emissionPointId;
    }

    public void setEmissionPointId(Long emissionPointId) {
        this.emissionPointId = emissionPointId;
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

    public Long getAddressCompanyId() {
        return addressCompanyId;
    }

    public void setAddressCompanyId(Long addressCompanyId) {
        this.addressCompanyId = addressCompanyId;
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
