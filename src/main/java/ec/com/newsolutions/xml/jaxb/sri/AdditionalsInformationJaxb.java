package ec.com.newsolutions.xml.jaxb.sri;

import ec.com.newsolutions.domain.AdditionalInformation;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "infoAdicional")
public class AdditionalsInformationJaxb {

    private List<String> additionalInformation;

    public AdditionalsInformationJaxb() {
        this.additionalInformation = new ArrayList<>();
    }

    @XmlElement(name = "campoAdicional")
    public List<String> getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(List<String> additionalInformation) {
        this.additionalInformation = additionalInformation;
    }
}
