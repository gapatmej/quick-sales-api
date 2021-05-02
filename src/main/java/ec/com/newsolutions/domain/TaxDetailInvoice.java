package ec.com.newsolutions.domain;

import ec.com.newsolutions.config.Constants;
import es.mityc.firmaJava.libreria.xades.elementos.xades.Int;

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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="tax_id", nullable = false)
    private Tax tax;

    @Column(name = "code", nullable = false)
    private int code;

    @Column(name = "percentage_code", length = 20, nullable = false)
    private Integer percentageCode;

    @Column(name = "rate", nullable = false)
    private Float rate;

    @Column(name = "tax_base", precision = 21, scale = 2, nullable = false)
    private BigDecimal taxBase;

    @Column(name = "amount", precision = 21, scale = 2, nullable = false)
    private BigDecimal amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="detail_invoice_client_id", nullable = false)
    private DetailInvoiceClient detailInvoiceClient;

    public TaxDetailInvoice() {
    }

    public TaxDetailInvoice(Tax tax, DetailInvoiceClient detailInvoiceClient) {
        this.tax = tax;
        this.code = Integer.parseInt(tax.getTaxType().codeTax());
        this.percentageCode = tax.getCode();
        this.rate = tax.getPercentage();
        this.taxBase = detailInvoiceClient.getTotal();
        this.amount = taxBase.multiply(BigDecimal.valueOf(this.rate)).divide(Constants.ONE_HUNDRED);
        this.detailInvoiceClient = detailInvoiceClient;
    }

    public Tax getTax() {
        return tax;
    }

    public void setTax(Tax tax) {
        this.tax = tax;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public Integer getPercentageCode() {
        return percentageCode;
    }

    public void setPercentageCode(Integer percentageCode) {
        this.percentageCode = percentageCode;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
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
