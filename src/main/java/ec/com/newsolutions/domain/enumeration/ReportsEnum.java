package ec.com.newsolutions.domain.enumeration;

public enum ReportsEnum {

    REPORT_INVOICE_CLIENT("electronicInvoiceClient.jasper");

    private final String jasperName;

    ReportsEnum(String jasperName) {
        this.jasperName= jasperName;
    }

    public String jasperName() {
        return jasperName;
    }
}
