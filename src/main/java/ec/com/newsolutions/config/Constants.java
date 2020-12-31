package ec.com.newsolutions.config;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;

/**
 * Application constants.
 */
public final class Constants {

    // Regex for acceptable logins
    public static final String DEFAULT_CHARSET = "UTF-8";
    public static final String LOGIN_REGEX = "^[_.@A-Za-z0-9-]*$";

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String DEFAULT_LANGUAGE = "es";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final SimpleDateFormat accessKeyFormatDate = new SimpleDateFormat("ddMMyyyy");
    public static final String numericCode = "01234567";

    public static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    private Constants() {
    }
}
