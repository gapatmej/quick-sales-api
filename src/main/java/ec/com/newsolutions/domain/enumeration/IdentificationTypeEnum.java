package ec.com.newsolutions.domain.enumeration;

/**
 * The IdentificationTypeEnum enumeration.
 */
public enum IdentificationTypeEnum {
    RUC("04"),
    IDENTIFICATION_CARD("05"),
    PASSPORT("06"),
    FINAL_CONSUMER("07");

    IdentificationTypeEnum(String code){
        this.code = code;
    }
    private final String code;

    public String code() {
        return code;
    }
}
