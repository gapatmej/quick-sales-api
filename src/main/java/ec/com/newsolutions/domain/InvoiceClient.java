package ec.com.newsolutions.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import ec.com.newsolutions.domain.enumeration.*;

/**
 * A InvoiceClient.
 */
@Entity
@Table(name = "invoice_client")
public class InvoiceClient extends AbstractAuditingEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="company_id", nullable = false)
    private Company company;

    @Enumerated(EnumType.STRING)
    @Column(name = "sri_enviroment", nullable = false)
    private SRIEnviromentEnum sriEnviroment;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "emission_type", nullable = false)
    private EmissionTypeEnum emissionType = EmissionTypeEnum.NORMAL;

    @NotNull
    @Column(name = "business_name", nullable = false)
    private String businessName;

    @Column(name = "accessKey", nullable = false, unique = true)
    private String accessKey;

    @Enumerated(EnumType.STRING)
    @Column(name = "receipt_type", nullable = false)
    private ReceiptTypeEnum receiptType;

    @NotNull
    @Column(name = "establishment_code", nullable = false)
    private String establishmentCode;

    @NotNull
    @Column(name = "emission_point_code", nullable = false)
    private String emissionPointCode;

    @NotNull
    @Column(name = "sequence", nullable = false)
    private String sequence;

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
    private CurrencyEnum currency = CurrencyEnum.DOLAR;

    @OneToMany(mappedBy = "invoice")
    private Set<Payment> payments = new HashSet<>();

    @OneToMany(mappedBy = "invoice", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<DetailInvoice> detailInvoices = new HashSet<>();

    @OneToMany(mappedBy = "invoice")
    private Set<TaxInvoice> taxInvoices = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="emission_point_id", nullable = false)
    private EmissionPoint emissionPoint;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Company getCompany() {
        return company;
    }

    public void setCompany(Company company) {
        this.company = company;
    }

    public SRIEnviromentEnum getSriEnviroment() {
        return sriEnviroment;
    }

    public void setSriEnviroment(SRIEnviromentEnum sriEnviroment) {
        this.sriEnviroment = sriEnviroment;
    }

    public EmissionTypeEnum getEmissionType() {
        return emissionType;
    }

    public void setEmissionType(EmissionTypeEnum emissionType) {
        this.emissionType = emissionType;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
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

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public Instant getDateIssue() {
        return dateIssue;
    }

    public void setDateIssue(Instant dateIssue) {
        this.dateIssue = dateIssue;
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

    public Set<DetailInvoice> getDetailInvoices() {
        return detailInvoices;
    }

    public void setDetailInvoices(Set<DetailInvoice> detailInvoices) {
        this.detailInvoices = detailInvoices;
    }

    public Set<TaxInvoice> getTaxInvoices() {
        return taxInvoices;
    }

    public void setTaxInvoices(Set<TaxInvoice> taxInvoices) {
        this.taxInvoices = taxInvoices;
    }

    public EmissionPoint getEmissionPoint() {
        return emissionPoint;
    }

    public void setEmissionPoint(EmissionPoint emissionPoint) {
        this.emissionPoint = emissionPoint;
    }
}
