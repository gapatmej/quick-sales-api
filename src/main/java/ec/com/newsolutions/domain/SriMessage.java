package ec.com.newsolutions.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sri_message")
public class SriMessage extends AbstractMainEntity  {

    @Column(name = "identificator", nullable = false)
    private Integer identificator;

    @Column(name = "message", length = 100, nullable = false)
    private String message;

    @Column(name = "additional_information", length = 500, nullable = false)
    private String additionalInformation;

    @Column(name = "type", length = 50, nullable = false)
    private String type;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="invoice_client_id")
    private InvoiceClient invoiceClient;

    public Integer getIdentificator() {
        return identificator;
    }

    public void setIdentificator(Integer identificator) {
        this.identificator = identificator;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public InvoiceClient getInvoiceClient() {
        return invoiceClient;
    }

    public void setInvoiceClient(InvoiceClient invoiceClient) {
        this.invoiceClient = invoiceClient;
    }
}
