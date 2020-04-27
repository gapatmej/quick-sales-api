package ec.com.newsolutions.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

import ec.com.newsolutions.domain.enumeration.TimeUnitEnum;

/**
 * A Payment.
 */
@Entity
@Table(name = "payment")
public class Payment implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "date", nullable = false)
    private Instant date;

    @NotNull
    @DecimalMin(value = "0")
    @Column(name = "amount", precision = 21, scale = 2, nullable = false)
    private BigDecimal amount;

    @Min(value = 0)
    @Column(name = "term")
    private Integer term;

    @Enumerated(EnumType.STRING)
    @Column(name = "time_unit")
    private TimeUnitEnum timeUnit;

    @Column(name = "decription")
    private String decription;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("payments")
    private PayWay wayPay;

    @ManyToOne
    @JsonIgnoreProperties("payments")
    private InvoiceClient invoice;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public Payment date(Instant date) {
        this.date = date;
        return this;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public Payment amount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getTerm() {
        return term;
    }

    public Payment term(Integer term) {
        this.term = term;
        return this;
    }

    public void setTerm(Integer term) {
        this.term = term;
    }

    public TimeUnitEnum getTimeUnit() {
        return timeUnit;
    }

    public Payment timeUnit(TimeUnitEnum timeUnit) {
        this.timeUnit = timeUnit;
        return this;
    }

    public void setTimeUnit(TimeUnitEnum timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getDecription() {
        return decription;
    }

    public Payment decription(String decription) {
        this.decription = decription;
        return this;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public PayWay getWayPay() {
        return wayPay;
    }

    public Payment wayPay(PayWay payWay) {
        this.wayPay = payWay;
        return this;
    }

    public void setWayPay(PayWay payWay) {
        this.wayPay = payWay;
    }

    public InvoiceClient getInvoice() {
        return invoice;
    }

    public Payment invoice(InvoiceClient invoiceClient) {
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
        if (!(o instanceof Payment)) {
            return false;
        }
        return id != null && id.equals(((Payment) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Payment{" +
            "id=" + getId() +
            ", date='" + getDate() + "'" +
            ", amount=" + getAmount() +
            ", term=" + getTerm() +
            ", timeUnit='" + getTimeUnit() + "'" +
            ", decription='" + getDecription() + "'" +
            "}";
    }
}
