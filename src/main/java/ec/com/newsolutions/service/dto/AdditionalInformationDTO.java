package ec.com.newsolutions.service.dto;

public class AdditionalInformationDTO extends AbstractMainDTO {

    private String additionalField;

    private String value;

    private Long invoiceClientId;

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

    public Long getInvoiceClientId() {
        return invoiceClientId;
    }

    public void setInvoiceClientId(Long invoiceClientId) {
        this.invoiceClientId = invoiceClientId;
    }
}
