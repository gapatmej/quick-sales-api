package ec.com.newsolutions.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "additional_information")
public class AdditionalInformation extends AbstractMainEntity  {

    @Column(name = "additional_field", length = 300, nullable = false)
    private String additionalField;

    @Column(name = "value", length = 300, nullable = false)
    private String value;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="invoice_client_id", nullable = false)
    private InvoiceClient invoiceClient;

    public String getAdditionalField() {
        return additionalField;
    }

    public void setAdditionalField(String additionalField) {
        this.additionalField = additionalField;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public InvoiceClient getInvoiceClient() {
        return invoiceClient;
    }

    public void setInvoiceClient(InvoiceClient invoiceClient) {
        this.invoiceClient = invoiceClient;
    }
}
