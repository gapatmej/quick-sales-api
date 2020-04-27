package ec.com.newsolutions.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * A TaxInvoice.
 */
@Entity
@Table(name = "tax_invoice")
public class TaxInvoice implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "code", nullable = false)
    private Integer code;

    @NotNull
    @Column(name = "percentage_code", nullable = false)
    private Integer percentageCode;

    @NotNull
    @Column(name = "tax_base", precision = 21, scale = 2, nullable = false)
    private BigDecimal taxBase;

    @NotNull
    @Column(name = "amount", precision = 21, scale = 2, nullable = false)
    private BigDecimal amount;

    @ManyToOne
    @JsonIgnoreProperties("taxInvoices")
    private InvoiceClient invoice;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getCode() {
        return code;
    }

    public TaxInvoice code(Integer code) {
        this.code = code;
        return this;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Integer getPercentageCode() {
        return percentageCode;
    }

    public TaxInvoice percentageCode(Integer percentageCode) {
        this.percentageCode = percentageCode;
        return this;
    }

    public void setPercentageCode(Integer percentageCode) {
        this.percentageCode = percentageCode;
    }

    public BigDecimal getTaxBase() {
        return taxBase;
    }

    public TaxInvoice taxBase(BigDecimal taxBase) {
        this.taxBase = taxBase;
        return this;
    }

    public void setTaxBase(BigDecimal taxBase) {
        this.taxBase = taxBase;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public TaxInvoice amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public InvoiceClient getInvoice() {
        return invoice;
    }

    public TaxInvoice invoice(InvoiceClient invoiceClient) {
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
        if (!(o instanceof TaxInvoice)) {
            return false;
        }
        return id != null && id.equals(((TaxInvoice) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "TaxInvoice{" +
            "id=" + getId() +
            ", code=" + getCode() +
            ", percentageCode=" + getPercentageCode() +
            ", taxBase=" + getTaxBase() +
            ", amount=" + getAmount() +
            "}";
    }
}
