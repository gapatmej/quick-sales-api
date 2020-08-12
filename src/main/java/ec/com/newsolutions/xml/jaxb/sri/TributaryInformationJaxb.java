package ec.com.newsolutions.xml.jaxb.sri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "infoTributaria")
@XmlAccessorType(XmlAccessType.FIELD)
public class TributaryInformationJaxb {

    private Integer environment;
    private Integer emissionType;
    private String businessName;
    private String tradename;
    private String identification;
    private String accessKey;
    private String codeDocument;
    private String establishmentCode;
    private String emissionPointCode;
    private String sequence;
    private String matrizAddress;

    public Integer getEnvironment() {
        return environment;
    }

    public void setEnvironment(Integer environment) {
        this.environment = environment;
    }

    public Integer getEmissionType() {
        return emissionType;
    }

    public void setEmissionType(Integer emissionType) {
        this.emissionType = emissionType;
    }

    public String getBusinessName() {
        return businessName;
    }

    public void setBusinessName(String businessName) {
        this.businessName = businessName;
    }

    public String getTradename() {
        return tradename;
    }

    public void setTradename(String tradename) {
        this.tradename = tradename;
    }

    public String getIdentification() {
        return identification;
    }

    public void setIdentification(String identification) {
        this.identification = identification;
    }

    public String getAccessKey() {
        return accessKey;
    }

    public void setAccessKey(String accessKey) {
        this.accessKey = accessKey;
    }

    public String getCodeDocument() {
        return codeDocument;
    }

    public void setCodeDocument(String codeDocument) {
        this.codeDocument = codeDocument;
    }

    public String getEstablishmentCode() {
        return establishmentCode;
    }

    public void setEstablishmentCode(String establishmentCode) {
        this.establishmentCode = establishmentCode;
    }

    public String getEmissionPointCode() {
        return emissionPointCode;
    }

    public void setEmissionPointCode(String emissionPointCode) {
        this.emissionPointCode = emissionPointCode;
    }

    public String getSequence() {
        return sequence;
    }

    public void setSequence(String sequence) {
        this.sequence = sequence;
    }

    public String getMatrizAddress() {
        return matrizAddress;
    }

    public void setMatrizAddress(String matrizAddress) {
        this.matrizAddress = matrizAddress;
    }
}
