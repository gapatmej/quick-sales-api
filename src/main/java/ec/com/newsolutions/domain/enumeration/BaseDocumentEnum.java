package ec.com.newsolutions.domain.enumeration;

public enum BaseDocumentEnum {
    INVOICE_CLIENT("Invoice Client");

    private final String description;

    BaseDocumentEnum(String description){
        this.description = description;
    }

    public String description() {
        return description;
    }
}
