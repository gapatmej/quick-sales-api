package ec.com.newsolutions.service.dto;

import ec.com.newsolutions.domain.enumeration.CurrencyEnum;
import ec.com.newsolutions.domain.enumeration.IdentificationTypeEnum;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class InvoiceClientDTO extends ElectronicDocumentDTO {

    private Long documentId;

    private Long emissionPointId;

    private Long companyId;

    private String businessName;

    private IdentificationTypeEnum identificationType;

    private String identification;

    private String address;

    private String phone;

    private String email;

    private BigDecimal totalTaxFree;

    private BigDecimal totalDiscount;

    private BigDecimal totalBaseTaxIVA;

    private BigDecimal totalBaseTaxICE;

    private BigDecimal totalTaxIVA;

    private BigDecimal totalTaxICE;

    private BigDecimal tip;

    private BigDecimal total;

    private CurrencyEnum currency;

    private List<PaymentDTO> payments  = new ArrayList<>();

    private List<DetailInvoiceClientDTO> detailInvoiceClients = new ArrayList<>();

    //private Set<TaxInvoice> taxInvoices = new HashSet<>();

    //private Set<AdditionalInformation> additionalInformations = new HashSet<>();

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getEmissionPointId() {
        return emissionPointId;
    }

    public void setEmissionPointId(Long emissionPointId) {
        this.emissionPointId = emissionPointId;
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

    public BigDecimal getTotalTaxFree() {
        return totalTaxFree;
    }

    public void setTotalTaxFree(BigDecimal totalTaxFree) {
        this.totalTaxFree = totalTaxFree;
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

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    public List<PaymentDTO> getPayments() {
        return payments;
    }

    public void setPayments(List<PaymentDTO> payments) {
        this.payments = payments;
    }

    public List<DetailInvoiceClientDTO> getDetailInvoiceClients() {
        return detailInvoiceClients;
    }

    public void setDetailInvoiceClients(List<DetailInvoiceClientDTO> detailInvoiceClients) {
        this.detailInvoiceClients = detailInvoiceClients;
    }
}
