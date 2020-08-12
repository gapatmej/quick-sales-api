package ec.com.newsolutions.xml.jaxb.sri;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.math.BigDecimal;

@XmlRootElement(name = "pago")
public class PaymentJaxb {

    private String payWay;
    private BigDecimal total;
    private BigDecimal timeLimit;
    private String timeUnit;

    @XmlElement(name = "formaPago")
    public String getPayWay() {
        return payWay;
    }

    public void setPayWay(String payWay) {
        this.payWay = payWay;
    }

    @XmlElement(name = "total")
    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

    @XmlElement(name = "plazo")
    public BigDecimal getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(BigDecimal timeLimit) {
        this.timeLimit = timeLimit;
    }

    @XmlElement(name = "unidadTiempo")
    public String getTimeUnit() {
        return timeUnit;
    }

    public void setTimeUnit(String timeUnit) {
        this.timeUnit = timeUnit;
    }
}
