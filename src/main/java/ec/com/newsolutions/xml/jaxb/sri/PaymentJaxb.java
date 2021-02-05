package ec.com.newsolutions.xml.jaxb.sri;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;

@XmlRootElement(name = "pago")

@XmlType(propOrder = { "payWay", "total", "timeLimit", "timeUnit" })
public class PaymentJaxb {

    private String payWay;
    private BigDecimal total;
    private int timeLimit;
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
    public int getTimeLimit() {
        return timeLimit;
    }

    public void setTimeLimit(int timeLimit) {
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
