package ec.com.newsolutions.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "tax_detail_invoice")
public class TaxDetailInvoice extends AbstractMainEntity {

    @Column(name = "code", nullable = false)
    private int code;

    @Column(name = "percentage_code", length = 20, nullable = false)
    private String percentageCode;

    @Column(name = "rate", nullable = false)
    private int rate;

    @Column(name = "tax_base", precision = 21, scale = 2, nullable = false)
    private BigDecimal taxBase;

    @Column(name = "amount", precision = 21, scale = 2, nullable = false)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="detail_invoice_client_id", nullable = false)
    private DetailInvoiceClient detailInvoiceClient;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getPercentageCode() {
        return percentageCode;
    }

    public void setPercentageCode(String percentageCode) {
        this.percentageCode = percentageCode;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
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

    public DetailInvoiceClient getDetailInvoiceClient() {
        return detailInvoiceClient;
    }

    public void setDetailInvoiceClient(DetailInvoiceClient detailInvoiceClient) {
        this.detailInvoiceClient = detailInvoiceClient;
    }
}
