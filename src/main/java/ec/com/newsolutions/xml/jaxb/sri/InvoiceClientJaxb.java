package ec.com.newsolutions.xml.jaxb.sri;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "factura")
public class InvoiceClientJaxb {
    private String id = "comprobante";
    private String version = "1.1.0";
    private TributaryInformationJaxb tributaryInformationJaxb;
    private InvoiceInformationJaxb invoiceInformationJaxb;


    @XmlAttribute(name = "id")
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute(name = "version")
    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @XmlElement(name="infoTributaria")
    public TributaryInformationJaxb getTributaryInformationJaxb() {
        return tributaryInformationJaxb;
    }

    public void setTributaryInformationJaxb(TributaryInformationJaxb tributaryInformationJaxb) {
        this.tributaryInformationJaxb = tributaryInformationJaxb;
    }

    @XmlElement(name="infoFactura")
    public InvoiceInformationJaxb getInvoiceInformationJaxb() {
        return invoiceInformationJaxb;
    }

    public void setInvoiceInformationJaxb(InvoiceInformationJaxb invoiceInformationJaxb) {
        this.invoiceInformationJaxb = invoiceInformationJaxb;
    }
}
