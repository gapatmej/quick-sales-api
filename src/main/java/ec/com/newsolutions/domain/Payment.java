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
    @Column(name = "time_limit")
    private int timeLimit;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Instant getDate() {
        return date;
    }

    public void setDate(Instant date) {
        this.date = date;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
        this.timeLimit = timeLimit;
    }

    public TimeUnitEnum getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(TimeUnitEnum timeUnit) {
        this.timeUnit = timeUnit;
    }

    public String getDecription() {
        return decription;
    }

    public void setDecription(String decription) {
        this.decription = decription;
    }

    public PayWay getWayPay() {
        return wayPay;
    }

    public void setWayPay(PayWay wayPay) {
        this.wayPay = wayPay;
    }

    public InvoiceClient getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceClient invoice) {
        this.invoice = invoice;
    }
}
