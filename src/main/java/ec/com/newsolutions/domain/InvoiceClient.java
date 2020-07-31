package ec.com.newsolutions.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import ec.com.newsolutions.domain.enumeration.SRIEnviromentEnum;

import ec.com.newsolutions.domain.enumeration.EmissionTypeEnum;

import ec.com.newsolutions.domain.enumeration.IdentificationTypeEnum;

import ec.com.newsolutions.domain.enumeration.CurrencyEnum;

/**
 * A InvoiceClient.
 */
@Entity
@Table(name = "invoice_client")
public class InvoiceClient implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "s_ri_enviroment", nullable = false)
    private SRIEnviromentEnum sRIEnviroment;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "emission_type", nullable = false)
    private EmissionTypeEnum emissionType;

    @NotNull
    @Column(name = "business_name", nullable = false)
    private String businessName;

    @NotNull
    @Column(name = "password", nullable = false, unique = true)
    private String password;

    @NotNull
    @Column(name = "code_document", nullable = false, unique = true)
    private String codeDocument;

    @NotNull
    @Column(name = "date_issue", nullable = false)
    private Instant dateIssue;

    @Enumerated(EnumType.STRING)
    @Column(name = "identification_type")
    private IdentificationTypeEnum identificationType;

    @NotNull
    @Column(name = "identification", nullable = false)
    private String identification;

    @Column(name = "address")
    private String address;

    @Column(name = "phone")
    private String phone;

    @NotNull
    @Column(name = "email", nullable = false)
    private String email;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "total_tax_free", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalTaxFree;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "total_discount", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalDiscount;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "total_base_tax_iva", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalBaseTaxIVA;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "total_base_tax_ice", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalBaseTaxICE;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "total_tax_iva", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalTaxIVA;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "total_tax_ice", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalTaxICE;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "tip", precision = 21, scale = 2, nullable = false)
    private BigDecimal tip;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "total", precision = 21, scale = 2, nullable = false)
    private BigDecimal total;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private CurrencyEnum currency;

    @OneToMany(mappedBy = "invoice")
    private Set<Payment> payments = new HashSet<>();

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetailInvoice> detailInvoices = new HashSet<>();

    @OneToMany(mappedBy = "invoice")
    private Set<TaxInvoice> taxInvoices = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id", nullable = false)
    @JsonIgnoreProperties("invoiceClients")
    private Company company;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="document_id", nullable = false)
    @JsonIgnoreProperties("invoiceClients")
    private Document document;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SRIEnviromentEnum getsRIEnviroment() {
        return sRIEnviroment;
    }

    public InvoiceClient sRIEnviroment(SRIEnviromentEnum sRIEnviroment) {
        this.sRIEnviroment = sRIEnviroment;
        return this;
    }

    public void setsRIEnviroment(SRIEnviromentEnum sRIEnviroment) {
        this.sRIEnviroment = sRIEnviroment;
    }

    public EmissionTypeEnum getEmissionType() {
        return emissionType;
    }

    public InvoiceClient emissionType(EmissionTypeEnum emissionType) {
        this.emissionType = emissionType;
        return this;
    }

    public void setEmissionType(EmissionTypeEnum emissionType) {
        this.emissionType = emissionType;
    }

    public String getBusinessName() {
        return businessName;
    }

    public InvoiceClient businessName(String businessName) {
        this.businessName = businessName;
        return this;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getPassword() {
        return password;
    }

    public InvoiceClient password(String password) {
        this.password = password;
        return this;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCodeDocument() {
        return codeDocument;
    }

    public InvoiceClient codeDocument(String codeDocument) {
        this.codeDocument = codeDocument;
        return this;
    }

    public void setCodeDocument(String codeDocument) {
        this.codeDocument = codeDocument;
    }

    public Instant getDateIssue() {
        return dateIssue;
    }

    public InvoiceClient dateIssue(Instant dateIssue) {
        this.dateIssue = dateIssue;
        return this;
    }

    public void setDateIssue(Instant dateIssue) {
        this.dateIssue = dateIssue;
    }

    public IdentificationTypeEnum getIdentificationType() {
        return identificationType;
    }

    public InvoiceClient identificationType(IdentificationTypeEnum identificationType) {
        this.identificationType = identificationType;
        return this;
    }

    public void setIdentificationType(IdentificationTypeEnum identificationType) {
        this.identificationType = identificationType;
    }

    public String getIdentification() {
        return identification;
    }

    public InvoiceClient identification(String identification) {
        this.identification = identification;
        return this;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getAddress() {
        return address;
    }

    public InvoiceClient address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public InvoiceClient phone(String phone) {
        this.phone = phone;
        return this;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public InvoiceClient email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getTotalTaxFree() {
        return totalTaxFree;
    }

    public InvoiceClient totalTaxFree(BigDecimal totalTaxFree) {
        this.totalTaxFree = totalTaxFree;
        return this;
    }

    public void setTotalTaxFree(BigDecimal totalTaxFree) {
        this.totalTaxFree = totalTaxFree;
    }

    public BigDecimal getTotalDiscount() {
        return totalDiscount;
    }

    public InvoiceClient totalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
        return this;
    }

    public void setTotalDiscount(BigDecimal totalDiscount) {
        this.totalDiscount = totalDiscount;
    }

    public BigDecimal getTotalBaseTaxIVA() {
        return totalBaseTaxIVA;
    }

    public InvoiceClient totalBaseTaxIVA(BigDecimal totalBaseTaxIVA) {
        this.totalBaseTaxIVA = totalBaseTaxIVA;
        return this;
    }

    public void setTotalBaseTaxIVA(BigDecimal totalBaseTaxIVA) {
        this.totalBaseTaxIVA = totalBaseTaxIVA;
    }

    public BigDecimal getTotalBaseTaxICE() {
        return totalBaseTaxICE;
    }

    public InvoiceClient totalBaseTaxICE(BigDecimal totalBaseTaxICE) {
        this.totalBaseTaxICE = totalBaseTaxICE;
        return this;
    }

    public void setTotalBaseTaxICE(BigDecimal totalBaseTaxICE) {
        this.totalBaseTaxICE = totalBaseTaxICE;
    }

    public BigDecimal getTotalTaxIVA() {
        return totalTaxIVA;
    }

    public InvoiceClient totalTaxIVA(BigDecimal totalTaxIVA) {
        this.totalTaxIVA = totalTaxIVA;
        return this;
    }

    public void setTotalTaxIVA(BigDecimal totalTaxIVA) {
        this.totalTaxIVA = totalTaxIVA;
    }

    public BigDecimal getTotalTaxICE() {
        return totalTaxICE;
    }

    public InvoiceClient totalTaxICE(BigDecimal totalTaxICE) {
        this.totalTaxICE = totalTaxICE;
        return this;
    }

    public void setTotalTaxICE(BigDecimal totalTaxICE) {
        this.totalTaxICE = totalTaxICE;
    }

    public BigDecimal getTip() {
        return tip;
    }

    public InvoiceClient tip(BigDecimal tip) {
        this.tip = tip;
        return this;
    }

    public void setTip(BigDecimal tip) {
        this.tip = tip;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public InvoiceClient total(BigDecimal total) {
        this.total = total;
        return this;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public CurrencyEnum getCurrency() {
        return currency;
    }

    public InvoiceClient currency(CurrencyEnum currency) {
        this.currency = currency;
        return this;
    }

    public void setCurrency(CurrencyEnum currency) {
        this.currency = currency;
    }

    public Set<Payment> getPayments() {
        return payments;
    }

    public InvoiceClient payments(Set<Payment> payments) {
        this.payments = payments;
        return this;
    }

    public InvoiceClient addPayment(Payment payment) {
        this.payments.add(payment);
        payment.setInvoice(this);
        return this;
    }

    public InvoiceClient removePayment(Payment payment) {
        this.payments.remove(payment);
        payment.setInvoice(null);
        return this;
    }

    public void setPayments(Set<Payment> payments) {
        this.payments = payments;
    }

    public Set<DetailInvoice> getDetailInvoices() {
        return detailInvoices;
    }

    public InvoiceClient detailInvoices(Set<DetailInvoice> detailInvoices) {
        this.detailInvoices = detailInvoices;
        return this;
    }

    public InvoiceClient addDetailInvoice(DetailInvoice detailInvoice) {
        this.detailInvoices.add(detailInvoice);
        detailInvoice.setInvoice(this);
        return this;
    }

    public InvoiceClient removeDetailInvoice(DetailInvoice detailInvoice) {
        this.detailInvoices.remove(detailInvoice);
        detailInvoice.setInvoice(null);
        return this;
    }

    public void setDetailInvoices(Set<DetailInvoice> detailInvoices) {
        this.detailInvoices = detailInvoices;
    }

    public Set<TaxInvoice> getTaxInvoices() {
        return taxInvoices;
    }

    public InvoiceClient taxInvoices(Set<TaxInvoice> taxInvoices) {
        this.taxInvoices = taxInvoices;
        return this;
    }

    public InvoiceClient addTaxInvoice(TaxInvoice taxInvoice) {
        this.taxInvoices.add(taxInvoice);
        taxInvoice.setInvoice(this);
        return this;
    }

    public InvoiceClient removeTaxInvoice(TaxInvoice taxInvoice) {
        this.taxInvoices.remove(taxInvoice);
        taxInvoice.setInvoice(null);
        return this;
    }

    public void setTaxInvoices(Set<TaxInvoice> taxInvoices) {
        this.taxInvoices = taxInvoices;
    }

    public Company getCompany() {
        return company;
    }

    public InvoiceClient client(Company company) {
        this.company = company;
        return this;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public Document getDocument() {
        return document;
    }

    public InvoiceClient document(Document document) {
        this.document = document;
        return this;
    }

    public void setDocument(Document document) {
        this.document = document;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof InvoiceClient)) {
            return false;
        }
        return id != null && id.equals(((InvoiceClient) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "InvoiceClient{" +
            "id=" + getId() +
            ", sRIEnviroment='" + getsRIEnviroment() + "'" +
            ", emissionType='" + getEmissionType() + "'" +
            ", businessName='" + getBusinessName() + "'" +
            ", password='" + getPassword() + "'" +
            ", codeDocument='" + getCodeDocument() + "'" +
            ", dateIssue='" + getDateIssue() + "'" +
            ", identificationType='" + getIdentificationType() + "'" +
            ", identification='" + getIdentification() + "'" +
            ", address='" + getAddress() + "'" +
            ", phone='" + getPhone() + "'" +
            ", email='" + getEmail() + "'" +
            ", totalTaxFree=" + getTotalTaxFree() +
            ", totalDiscount=" + getTotalDiscount() +
            ", totalBaseTaxIVA=" + getTotalBaseTaxIVA() +
            ", totalBaseTaxICE=" + getTotalBaseTaxICE() +
            ", totalTaxIVA=" + getTotalTaxIVA() +
            ", totalTaxICE=" + getTotalTaxICE() +
            ", tip=" + getTip() +
            ", total=" + getTotal() +
            ", currency='" + getCurrency() + "'" +
            "}";
    }
}
