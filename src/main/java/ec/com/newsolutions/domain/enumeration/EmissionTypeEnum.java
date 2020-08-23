package ec.com.newsolutions.domain.enumeration;

/**
 * The EmissionTypeEnum enumeration.
 */
public enum EmissionTypeEnum {
    NORMAL (1);

    private final int code;

    EmissionTypeEnum(int code){
        this.code = code;
    }

    public int code() {
        return code;
    }
}
