package ec.com.newsolutions.xml.jaxb.sri;

import ec.com.newsolutions.domain.AdditionalInformation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "infoAdicional")
public class AdditionalsInformationJaxb {

    private List<String> additionalInformation;

    @XmlElement(name = "campoAdicional")
    public List<String> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(List<String> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
