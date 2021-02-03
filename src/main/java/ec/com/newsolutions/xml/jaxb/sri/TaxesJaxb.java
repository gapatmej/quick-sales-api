package ec.com.newsolutions.xml.jaxb.sri;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "impuestos")
public class TaxesJaxb {
    private List<TaxJaxb> taxJaxb;

    public TaxesJaxb() {
        this.taxJaxb = new ArrayList<>();
    }

    @XmlElement(name = "impuesto")
    public List<TaxJaxb> getTaxJaxb() {
        return taxJaxb;
    }

    public void setTaxJaxb(List<TaxJaxb> taxJaxb) {
        this.taxJaxb = taxJaxb;
    }
}
