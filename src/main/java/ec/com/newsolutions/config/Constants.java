package ec.com.newsolutions.config;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

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
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT_1 = new SimpleDateFormat("ddMMyyyy");
    public static final SimpleDateFormat SIMPLE_DATE_FORMAT_2 = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
    public static final String numericCode = "01234567";
    public static final DateTimeFormatter DATE_TIME_FORMATTER_1 = DateTimeFormatter.ofPattern("dd/MM/yyyy").withZone(ZoneId.systemDefault());

    public static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

    private Constants() {
    }
}
