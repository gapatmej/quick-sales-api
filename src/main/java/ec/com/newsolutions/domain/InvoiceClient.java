package ec.com.newsolutions.domain;

import ec.com.newsolutions.domain.enumeration.CurrencyEnum;
import ec.com.newsolutions.domain.enumeration.IdentificationTypeEnum;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "invoice_client")
public class InvoiceClient extends TributaryDocument {

    @Column(name = "total_without_tax", precision = 21, scale = 2, nullable = false)
    private BigDecimal totalWithoutTax;

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
    private Set<DetailInvoiceClient> detailsInvoiceClient = new HashSet<>();

    @OneToMany(mappedBy = "invoiceClient", fetch = FetchType.LAZY)
    private Set<TaxInvoice> taxesInvoice = new HashSet<>();

    @OneToMany(mappedBy = "invoiceClient", fetch = FetchType.LAZY)
    private Set<AdditionalInformation> additionalsInformation = new HashSet<>();

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

    public Set<DetailInvoiceClient> getDetailsInvoiceClient() {
        return detailsInvoiceClient;
    }

    public void setDetailsInvoiceClient(Set<DetailInvoiceClient> detailsInvoiceClient) {
        this.detailsInvoiceClient = detailsInvoiceClient;
    }

    public Set<TaxInvoice> getTaxesInvoice() {
        return taxesInvoice;
    }

    public void setTaxesInvoice(Set<TaxInvoice> taxesInvoice) {
        this.taxesInvoice = taxesInvoice;
    }

    public Set<AdditionalInformation> getAdditionalsInformation() {
        return additionalsInformation;
    }

    public void setAdditionalsInformation(Set<AdditionalInformation> additionalsInformation) {
        this.additionalsInformation = additionalsInformation;
    }

}
