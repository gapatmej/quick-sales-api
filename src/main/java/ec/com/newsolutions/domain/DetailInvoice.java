package ec.com.newsolutions.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * A DetailInvoice.
 */
@Entity
@Table(name = "detail_invoice")
public class DetailInvoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "main_code", nullable = false)
    private String mainCode;

    @Column(name = "auxiliary_code")
    private String auxiliaryCode;

    @Column(name = "description")
    private String description;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "quantity", precision = 21, scale = 2, nullable = false)
    private BigDecimal quantity;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "unit_price", precision = 21, scale = 2, nullable = false)
    private BigDecimal unitPrice;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "discount", precision = 21, scale = 2, nullable = false)
    private BigDecimal discount;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "total", precision = 21, scale = 2, nullable = false)
    private BigDecimal total;

    @OneToMany(mappedBy = "detailInvoice")
    private Set<TaxDetailInvoice> taxDetailInvoices = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("detailInvoices")
    private Product product;

    @ManyToOne
    @JsonIgnoreProperties("detailInvoices")
    private InvoiceClient invoice;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMainCode() {
        return mainCode;
    }

    public DetailInvoice mainCode(String mainCode) {
        this.mainCode = mainCode;
        return this;
    }

    public void setMainCode(String mainCode) {
        this.mainCode = mainCode;
    }

    public String getAuxiliaryCode() {
        return auxiliaryCode;
    }

    public DetailInvoice auxiliaryCode(String auxiliaryCode) {
        this.auxiliaryCode = auxiliaryCode;
        return this;
    }

    public void setAuxiliaryCode(String auxiliaryCode) {
        this.auxiliaryCode = auxiliaryCode;
    }

    public String getDescription() {
        return description;
    }

    public DetailInvoice description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public DetailInvoice quantity(BigDecimal quantity) {
        this.quantity = quantity;
        return this;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public DetailInvoice unitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public DetailInvoice discount(BigDecimal discount) {
        this.discount = discount;
        return this;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public DetailInvoice total(BigDecimal total) {
        this.total = total;
        return this;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Set<TaxDetailInvoice> getTaxDetailInvoices() {
        return taxDetailInvoices;
    }

    public DetailInvoice taxDetailInvoices(Set<TaxDetailInvoice> taxDetailInvoices) {
        this.taxDetailInvoices = taxDetailInvoices;
        return this;
    }

    public DetailInvoice addTaxDetailInvoice(TaxDetailInvoice taxDetailInvoice) {
        this.taxDetailInvoices.add(taxDetailInvoice);
        taxDetailInvoice.setDetailInvoice(this);
        return this;
    }

    public DetailInvoice removeTaxDetailInvoice(TaxDetailInvoice taxDetailInvoice) {
        this.taxDetailInvoices.remove(taxDetailInvoice);
        taxDetailInvoice.setDetailInvoice(null);
        return this;
    }

    public void setTaxDetailInvoices(Set<TaxDetailInvoice> taxDetailInvoices) {
        this.taxDetailInvoices = taxDetailInvoices;
    }

    public Product getProduct() {
        return product;
    }

    public DetailInvoice product(Product product) {
        this.product = product;
        return this;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public InvoiceClient getInvoice() {
        return invoice;
    }

    public DetailInvoice invoice(InvoiceClient invoiceClient) {
        this.invoice = invoiceClient;
        return this;
    }

    public void setInvoice(InvoiceClient invoiceClient) {
        this.invoice = invoiceClient;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof DetailInvoice)) {
            return false;
        }
        return id != null && id.equals(((DetailInvoice) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "DetailInvoice{" +
            "id=" + getId() +
            ", mainCode='" + getMainCode() + "'" +
            ", auxiliaryCode='" + getAuxiliaryCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", quantity=" + getQuantity() +
            ", unitPrice=" + getUnitPrice() +
            ", discount=" + getDiscount() +
            ", total=" + getTotal() +
            "}";
    }
}
