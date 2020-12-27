package ec.com.newsolutions.domain;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

import ec.com.newsolutions.domain.enumeration.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "invoice_client")
public class InvoiceClient extends ElectronicDocument{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="document_id", nullable = false)
    private Document document;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="emission_point_id", nullable = false)
    private EmissionPoint emissionPoint;

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

    @Column(name = "address", length = 200, nullable = false)
    private String address;

    @Column(name = "phone", length = 13)
    private String phone;

    @Column(name = "email", length = 200, nullable = false)
    private String email;

    @Column(name = "total_tax_free", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalTaxFree;

    @Column(name = "total_discount", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalDiscount;

    @Column(name = "total_base_tax_iva", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalBaseTaxIVA;

    @Column(name = "total_base_tax_ice", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalBaseTaxICE;

    @Column(name = "total_tax_iva", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalTaxIVA;

    @Column(name = "total_tax_ice", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalTaxICE;

    @Column(name = "tip", precision = 21, scale = 2, nullable = false)
    private BigDecimal tip;

    @Column(name = "total", precision = 21, scale = 2, nullable = false)
    private BigDecimal total;

    @Enumerated(EnumType.STRING)
    @Column(name = "currency", length = 20, nullable = false)
    private CurrencyEnum currency = CurrencyEnum.DOLAR;

    @OneToMany(mappedBy = "invoiceClient", fetch = FetchType.LAZY)
    private Set<Payment> payments = new HashSet<>();

    @OneToMany(mappedBy = "invoiceClient", fetch = FetchType.LAZY)
    private Set<DetailInvoiceClient> detailInvoiceClients = new HashSet<>();

    @OneToMany(mappedBy = "invoiceClient", fetch = FetchType.LAZY)
    private Set<TaxInvoice> taxInvoices = new HashSet<>();

    @OneToMany(mappedBy = "invoiceClient", fetch = FetchType.LAZY)
    private Set<AdditionalInformation> additionalInformations = new HashSet<>();

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public EmissionPoint getEmissionPoint() {
        return emissionPoint;
    }

    public void setEmissionPoint(EmissionPoint emissionPoint) {
        this.emissionPoint = emissionPoint;
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

    public Set<Payment> getPayments() {
        return payments;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public Set<DetailInvoiceClient> getDetailInvoiceClients() {
        return detailInvoiceClients;
    }

    public void setDetailInvoiceClients(Set<DetailInvoiceClient> detailInvoiceClients) {
        this.detailInvoiceClients = detailInvoiceClients;
    }

    public Set<TaxInvoice> getTaxInvoices() {
        return taxInvoices;
    }

    public void setTaxInvoices(Set<TaxInvoice> taxInvoices) {
        this.taxInvoices = taxInvoices;
    }

    public Set<AdditionalInformation> getAdditionalInformations() {
        return additionalInformations;
    }

    public void setAdditionalInformations(Set<AdditionalInformation> additionalInformations) {
        this.additionalInformations = additionalInformations;
    }
}
