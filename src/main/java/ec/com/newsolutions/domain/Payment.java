package ec.com.newsolutions.domain;

import java.math.BigDecimal;
import java.time.Instant;

import ec.com.newsolutions.domain.enumeration.TimeUnitEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


@Entity
@Table(name = "payment")
public class Payment extends AbstractMainEntity {

    @Column(name = "date", nullable = false)
    private Instant date;

    @Column(name = "amount", precision = 21, scale = 2, nullable = false)
    private BigDecimal amount;

    @Column(name = "time_limit")
    private int timeLimit;

    @Enumerated(EnumType.STRING)
    @Column(name = "time_unit", length = 20)
    private TimeUnitEnum timeUnit;

    @Column(name = "decription", length = 200)
    private String decription;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="pay_way_id", nullable = false)
    private PayWay payWay;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="invoice_id", nullable = false)
    private InvoiceClient invoiceClient;

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

    public PayWay getPayWay() {
        return payWay;
    }

    public void setPayWay(PayWay payWay) {
        this.payWay = payWay;
    }

    public InvoiceClient getInvoiceClient() {
        return invoiceClient;
    }

    public void setInvoiceClient(InvoiceClient invoiceClient) {
        this.invoiceClient = invoiceClient;
    }
}
