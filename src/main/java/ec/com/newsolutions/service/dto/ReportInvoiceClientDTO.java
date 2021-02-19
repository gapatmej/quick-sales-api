package ec.com.newsolutions.service.dto;

import ec.com.newsolutions.domain.enumeration.EmissionTypeEnum;
import ec.com.newsolutions.domain.enumeration.ReceiptTypeEnum;
import ec.com.newsolutions.domain.enumeration.SRIDocumentStateEnum;
import ec.com.newsolutions.domain.enumeration.SRIEnvironmentEnum;

import java.math.BigDecimal;
import java.time.Instant;

public class ReportInvoiceClientDTO {

    private SRIEnvironmentEnum sriEnvironment;

    private EmissionTypeEnum emissionType ;

    private String accessKey;

    private ReceiptTypeEnum receiptType;

    private SRIDocumentStateEnum sriDocumentState;

    private Instant authorizationDate;

    private String businessName;

    private String identification;

    private String establishmentCode;

    private String emissionPointCode;

    private int sequence;

    private Instant dateIssue;

    private String address;

    private String phone;

    private String email;

    private String detailMainCode;

    private String detailAuxiliaryCode;

    private String detailDescription;

    private BigDecimal detailQuantity;

    private BigDecimal detailUnitPrice;

    private BigDecimal detailDiscount;

    private BigDecimal detailTotal;

    private BigDecimal totalWithoutTax;

    private BigDecimal totalDiscount;

    private BigDecimal totalBaseTaxIVA;

    private BigDecimal totalBaseTaxICE;

    private BigDecimal totalTaxIVA;

    private BigDecimal totalTaxICE;

    private BigDecimal tip;

    private BigDecimal total;

    public ReportInvoiceClientDTO(SRIEnvironmentEnum sriEnvironment, EmissionTypeEnum emissionType, String accessKey, ReceiptTypeEnum receiptType,
                                  SRIDocumentStateEnum sriDocumentState, Instant authorizationDate, String businessName, String identification,
                                  String establishmentCode, String emissionPointCode, int sequence, Instant dateIssue, String address, String phone,
                                  String email, String detailMainCode, String detailAuxiliaryCode, String detailDescription, BigDecimal detailQuantity,
                                  BigDecimal detailUnitPrice, BigDecimal detailDiscount, BigDecimal detailTotal, BigDecimal totalWithoutTax,
                                  BigDecimal totalDiscount, BigDecimal totalBaseTaxIVA, BigDecimal totalBaseTaxICE, BigDecimal totalTaxIVA,
                                  BigDecimal totalTaxICE, BigDecimal tip, BigDecimal total) {
        this.sriEnvironment = sriEnvironment;
        this.emissionType = emissionType;
        this.accessKey = accessKey;
        this.receiptType = receiptType;
        this.sriDocumentState = sriDocumentState;
        this.authorizationDate = authorizationDate;
        this.businessName = businessName;
        this.identification = identification;
        this.establishmentCode = establishmentCode;
        this.emissionPointCode = emissionPointCode;
        this.sequence = sequence;
        this.dateIssue = dateIssue;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.detailMainCode = detailMainCode;
        this.detailAuxiliaryCode = detailAuxiliaryCode;
        this.detailDescription = detailDescription;
        this.detailQuantity = detailQuantity;
        this.detailUnitPrice = detailUnitPrice;
        this.detailDiscount = detailDiscount;
        this.detailTotal = detailTotal;
        this.totalWithoutTax = totalWithoutTax;
        this.totalDiscount = totalDiscount;
        this.totalBaseTaxIVA = totalBaseTaxIVA;
        this.totalBaseTaxICE = totalBaseTaxICE;
        this.totalTaxIVA = totalTaxIVA;
        this.totalTaxICE = totalTaxICE;
        this.tip = tip;
        this.total = total;
    }

    public SRIEnvironmentEnum getSriEnvironment() {
        return sriEnvironment;
    }

    public void setSriEnvironment(SRIEnvironmentEnum sriEnvironment) {
        this.sriEnvironment = sriEnvironment;
    }

    public EmissionTypeEnum getEmissionType() {
        return emissionType;
    }

    public void setEmissionType(EmissionTypeEnum emissionType) {
        this.emissionType = emissionType;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public ReceiptTypeEnum getReceiptType() {
        return receiptType;
    }

    public void setReceiptType(ReceiptTypeEnum receiptType) {
        this.receiptType = receiptType;
    }

    public SRIDocumentStateEnum getSriDocumentState() {
        return sriDocumentState;
    }

    public void setSriDocumentState(SRIDocumentStateEnum sriDocumentState) {
        this.sriDocumentState = sriDocumentState;
    }

    public Instant getAuthorizationDate() {
        return authorizationDate;
    }

    public void setAuthorizationDate(Instant authorizationDate) {
        this.authorizationDate = authorizationDate;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
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

    public String getDetailMainCode() {
        return detailMainCode;
    }

    public void setDetailMainCode(String detailMainCode) {
        this.detailMainCode = detailMainCode;
    }

    public String getDetailAuxiliaryCode() {
        return detailAuxiliaryCode;
    }

    public void setDetailAuxiliaryCode(String detailAuxiliaryCode) {
        this.detailAuxiliaryCode = detailAuxiliaryCode;
    }

    public String getDetailDescription() {
        return detailDescription;
    }

    public void setDetailDescription(String detailDescription) {
        this.detailDescription = detailDescription;
    }

    public BigDecimal getDetailQuantity() {
        return detailQuantity;
    }

    public void setDetailQuantity(BigDecimal detailQuantity) {
        this.detailQuantity = detailQuantity;
    }

    public BigDecimal getDetailUnitPrice() {
        return detailUnitPrice;
    }

    public void setDetailUnitPrice(BigDecimal detailUnitPrice) {
        this.detailUnitPrice = detailUnitPrice;
    }

    public BigDecimal getDetailDiscount() {
        return detailDiscount;
    }

    public void setDetailDiscount(BigDecimal detailDiscount) {
        this.detailDiscount = detailDiscount;
    }

    public BigDecimal getDetailTotal() {
        return detailTotal;
    }

    public void setDetailTotal(BigDecimal detailTotal) {
        this.detailTotal = detailTotal;
    }

    public BigDecimal getTotalWithoutTax() {
        return totalWithoutTax;
    }

    public void setTotalWithoutTax(BigDecimal totalWithoutTax) {
        this.totalWithoutTax = totalWithoutTax;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public BigDecimal getTotalBaseTaxIVA() {
        return totalBaseTaxIVA;
    }

    public void setTotalBaseTaxIVA(BigDecimal totalBaseTaxIVA) {
        this.totalBaseTaxIVA = totalBaseTaxIVA;
    }

    public BigDecimal getTotalBaseTaxICE() {
        return totalBaseTaxICE;
    }

    public void setTotalBaseTaxICE(BigDecimal totalBaseTaxICE) {
        this.totalBaseTaxICE = totalBaseTaxICE;
    }

    public BigDecimal getTotalTaxIVA() {
        return totalTaxIVA;
    }

    public void setTotalTaxIVA(BigDecimal totalTaxIVA) {
        this.totalTaxIVA = totalTaxIVA;
    }

    public BigDecimal getTotalTaxICE() {
        return totalTaxICE;
    }

    public void setTotalTaxICE(BigDecimal totalTaxICE) {
        this.totalTaxICE = totalTaxICE;
    }

    public BigDecimal getTip() {
        return tip;
    }

    public void setTip(BigDecimal tip) {
        this.tip = tip;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }
}
