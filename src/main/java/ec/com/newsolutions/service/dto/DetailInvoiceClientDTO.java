package ec.com.newsolutions.service.dto;

import java.math.BigDecimal;

public class DetailInvoiceClientDTO extends AbstractMainDTO {

    private String mainCode;

    private String auxiliaryCode;

    private String description;

    private BigDecimal quantity;

    private BigDecimal unitPrice;

    private BigDecimal discount;

    private BigDecimal total;

   // private Set<TaxDetailInvoice> taxDetailInvoices = new HashSet<>();

    private Long productId;

    private Long invoiceClientId;

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

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public Long getInvoiceClientId() {
        return invoiceClientId;
    }

    public void setInvoiceClientId(Long invoiceClientId) {
        this.invoiceClientId = invoiceClientId;
    }
}
