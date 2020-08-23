package ec.com.newsolutions.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A TaxDetailInvoice.
 */
@Entity
@Table(name = "tax_detail_invoice")
public class TaxDetailInvoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private int code;

    @NotNull
    @Column(name = "percentage_code", nullable = false)
    private Integer percentageCode;

    @NotNull
    @Column(name = "rate", nullable = false)
    private Integer rate;

    @NotNull
    @Column(name = "tax_base", precision = 21, scale = 2, nullable = false)
    private BigDecimal taxBase;

    @NotNull
    @Column(name = "amount", precision = 21, scale = 2, nullable = false)
    private BigDecimal amount;

    @ManyToOne
    private DetailInvoice detailsInvoice;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Integer getRate() {
        return rate;
    }

    public void setRate(Integer rate) {
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

    public DetailInvoice getDetailsInvoice() {
        return detailsInvoice;
    }

    public void setDetailsInvoice(DetailInvoice detailsInvoice) {
        this.detailsInvoice = detailsInvoice;
    }
}
