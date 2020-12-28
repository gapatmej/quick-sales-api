package ec.com.newsolutions.service.dto;

import java.math.BigDecimal;

public class TaxInvoiceDTO extends AbstractMainDTO {

    private Long taxId;

    private int code;

    private int percentageCode;

    private BigDecimal taxBase;

    private BigDecimal amount;

    private Long invoiceClientId;

    public int getCode() {
        return code;
    }

    public Long getTaxId() {
        return taxId;
    }

    public void setTaxId(Long taxId) {
        this.taxId = taxId;
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

    public Long getInvoiceClientId() {
        return invoiceClientId;
    }

    public void setInvoiceClientId(Long invoiceClientId) {
        this.invoiceClientId = invoiceClientId;
    }
}
