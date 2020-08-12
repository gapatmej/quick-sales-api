package ec.com.newsolutions.xml.jaxb.sri;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "totalConImpuestos")
public class TotalWithTaxesJaxb {
    private List<TotalTaxJaxb> totalTaxJaxb;

    @XmlElement(name = "totalImpuesto")
    public List<TotalTaxJaxb> getTotalTaxJaxb() {
        return totalTaxJaxb;
    }

    public void setTotalTaxJaxb(List<TotalTaxJaxb> totalTaxJaxb) {
        this.totalTaxJaxb = totalTaxJaxb;
    }
}
