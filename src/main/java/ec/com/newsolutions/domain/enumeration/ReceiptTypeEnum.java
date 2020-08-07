package ec.com.newsolutions.domain.enumeration;

/**
 * The DocumentTypeEnum enumeration.
 */
public enum ReceiptTypeEnum {
    INVOICE("01","FACTURA"),
    PURCHASE_CLEARANCE("03","LIQUIDACIÓN DE COMPRA DE BIENES Y PRESTACIÓN DE SERVICIOS"),
    CREDIT_NOTE("04","NOTA DE CRÉDITO"),
    DEBIT_NOTE("05","NOTA DE DÉBITO  "),
    REFERRAL_GUIDE("06","GUÍA DE REMISIÓN"),
    RETENTION_RECEIPT("07","COMPROBANTE DE RETENCIÓN");

    private final String code;
    private final String receipt;

    ReceiptTypeEnum(String code, String receipt) {
        this.code = code;
        this.receipt = receipt;
    }

    public String code() {
        return code;
    }
    public String receipt() {
        return receipt;
    }

}
