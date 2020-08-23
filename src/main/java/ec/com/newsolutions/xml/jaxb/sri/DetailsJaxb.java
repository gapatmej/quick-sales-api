package ec.com.newsolutions.xml.jaxb.sri;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "detalles")
public class DetailsJaxb {

    private List<DetailJaxb> detailJaxb;

    @XmlElement(name = "detalle")
    public List<DetailJaxb> getDetailJaxb() {
        return detailJaxb;
    }

    public void setDetailJaxb(List<DetailJaxb> detailJaxb) {
        this.detailJaxb = detailJaxb;
    }
}
