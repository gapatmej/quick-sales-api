package ec.com.newsolutions.xml.jaxb.sri;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "infoAdicional")
public class AdditionalsInformationJaxb {

    private List<AdditionalFieldJaxb> additionalFieldJaxbs;

    public AdditionalsInformationJaxb() {
        this.additionalFieldJaxbs = new ArrayList<>();
    }

    @XmlElement(name = "campoAdicional")
    public List<AdditionalFieldJaxb> getAdditionalFieldJaxbs() {
        return additionalFieldJaxbs;
    }

    public void setAdditionalFieldJaxbs(List<AdditionalFieldJaxb> additionalFieldJaxbs) {
        this.additionalFieldJaxbs = additionalFieldJaxbs;
    }
}
