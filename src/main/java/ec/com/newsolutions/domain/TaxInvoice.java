package ec.com.newsolutions.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "tax_invoice")
public class TaxInvoice extends AbstractMainEntity {

    @Column(name = "code", nullable = false)
    private int code;

    @Column(name = "percentage_code", nullable = false)
    private int percentageCode;

    @Column(name = "tax_base", precision = 21, scale = 2, nullable = false)
    private BigDecimal taxBase;

    @Column(name = "amount", precision = 21, scale = 2, nullable = false)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="invoice_id", nullable = false)
    private InvoiceClient invoiceClient;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPercentageCode() {
        return percentageCode;
    }

    public void setPercentageCode(int percentageCode) {
        this.percentageCode = percentageCode;
    }

    public BigDecimal getTaxBase() {
        return taxBase;
    }

    public void setTaxBase(BigDecimal taxBase) {
        this.taxBase = taxBase;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public InvoiceClient getInvoiceClient() {
        return invoiceClient;
    }

    public void setInvoiceClient(InvoiceClient invoiceClient) {
        this.invoiceClient = invoiceClient;
    }
}
