package ec.com.newsolutions.domain.enumeration;

/**
 * The SRIEnviromentEnum enumeration.
 */
public enum SRIEnvironmentEnum {
    TEST(1),
    PRODUCTION(2);

    private final int code;

    SRIEnvironmentEnum(int code){
        this.code = code;
    }

    public int code() {
        return code;
    }
}
