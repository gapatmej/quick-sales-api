package ec.com.newsolutions.service.dto;

import ec.com.newsolutions.domain.enumeration.TimeUnitEnum;
import java.math.BigDecimal;
import java.time.Instant;

public class PaymentDTO extends AbstractMainDTO {

    private Instant date;

    private BigDecimal amount;

    private int timeLimit;

    private TimeUnitEnum timeUnit;

    private String decription;

    private Long payWayId;

    private Long invoiceClientId;

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

    public Long getPayWayId() {
        return payWayId;
    }

    public void setPayWayId(Long payWayId) {
        this.payWayId = payWayId;
    }

    public Long getInvoiceClientId() {
        return invoiceClientId;
    }

    public void setInvoiceClientId(Long invoiceClientId) {
        this.invoiceClientId = invoiceClientId;
    }
}
