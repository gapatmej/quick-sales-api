package ec.com.newsolutions.xml.jaxb.sri;

import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "detalles")
public class DetailsJaxb {

    private List<DetailJaxb> detailJaxb;
}
