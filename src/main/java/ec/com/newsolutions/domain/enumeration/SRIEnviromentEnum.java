package ec.com.newsolutions.domain.enumeration;

/**
 * The SRIEnviromentEnum enumeration.
 */
public enum SRIEnviromentEnum {
    TEST(1),
    PRODUCTION(2);

    private final int code;

    SRIEnviromentEnum(int code){
        this.code = code;
    }

    public int code() {
        return code;
    }
}
