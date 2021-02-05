package ec.com.newsolutions.xml.jaxb.sri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "infoTributaria")
@XmlAccessorType(XmlAccessType.FIELD)
public class TributaryInformationJaxb {

    @XmlElement(name = "ambiente")
    private Integer environment;
    @XmlElement(name = "tipoEmision")
    private Integer emissionType;
    @XmlElement(name = "razonSocial")
    private String businessName;
    @XmlElement(name = "nombreComercial")
    private String tradename;
    @XmlElement(name = "ruc")
    private String identification;
    @XmlElement(name = "claveAcceso")
    private String accessKey;
    @XmlElement(name = "codDoc")
    private String codeDocument;
    @XmlElement(name = "estab")
    private String establishmentCode;
    @XmlElement(name = "ptoEmi")
    private String emissionPointCode;
    @XmlElement(name = "secuencial")
    private String sequence;
    @XmlElement(name = "dirMatriz")
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
