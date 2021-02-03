package ec.com.newsolutions.xml.jaxb.sri;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "pagos")
public class PaymentsJaxb {

    private List<PaymentJaxb> payment;

    public PaymentsJaxb() {
        this.payment = new ArrayList<>();
    }

    @XmlElement(name = "pago")
    public List<PaymentJaxb> getPayment() {
        return payment;
    }

    public void setPayment(List<PaymentJaxb> payment) {
        this.payment = payment;
    }
}
