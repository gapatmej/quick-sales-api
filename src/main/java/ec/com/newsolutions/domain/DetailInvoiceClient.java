package ec.com.newsolutions.domain;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "detail_invoice_client")
public class DetailInvoiceClient extends AbstractMainEntity {

    @Column(name = "main_code", length = 50, nullable = false)
    private String mainCode;

    @Column(name = "auxiliary_code", length = 50)
    private String auxiliaryCode;

    @Column(name = "description", length = 200, nullable = false)
    private String description;

    @Column(name = "quantity", precision = 21, scale = 2, nullable = false)
    private BigDecimal quantity;

    @Column(name = "unit_price", precision = 21, scale = 2, nullable = false)
    private BigDecimal unitPrice;

    @Column(name = "discount", precision = 21, scale = 2, nullable = false)
    private BigDecimal discount;

    @Column(name = "total", precision = 21, scale = 2, nullable = false)
    private BigDecimal total;

    @OneToMany(mappedBy = "detailInvoiceClient")
    private Set<TaxDetailInvoice> taxesDetailInvoice = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="product_id", nullable = false)
    private Product product;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="invoice_client_id", nullable = false)
    private InvoiceClient invoiceClient;

    @Transient
    private Boolean deleted;

    public String getMainCode() {
        return mainCode;
    }

    public void setMainCode(String mainCode) {
        this.mainCode = mainCode;
    }

    public String getAuxiliaryCode() {
        return auxiliaryCode;
    }

    public void setAuxiliaryCode(String auxiliaryCode) {
        this.auxiliaryCode = auxiliaryCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getQuantity() {
        return quantity;
    }

    public void setQuantity(BigDecimal quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(BigDecimal unitPrice) {
        this.unitPrice = unitPrice;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    public Set<TaxDetailInvoice> getTaxesDetailInvoice() {
        return taxesDetailInvoice;
    }

    public void setTaxesDetailInvoice(Set<TaxDetailInvoice> taxesDetailInvoice) {
        this.taxesDetailInvoice = taxesDetailInvoice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public InvoiceClient getInvoiceClient() {
        return invoiceClient;
    }

    public void setInvoiceClient(InvoiceClient invoiceClient) {
        this.invoiceClient = invoiceClient;
    }

    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }
}
