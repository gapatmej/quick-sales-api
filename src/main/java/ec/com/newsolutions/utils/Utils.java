package ec.com.newsolutions.utils;

import ec.com.newsolutions.config.Constants;

import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class Utils {

    public static final String DIRECTORY_SEPARATOR = "/";

    public static String escapeCharacters(String string, char... chars){
        for (char c: chars) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("\\");
            stringBuilder.append(c);
            string = string.replaceAll(stringBuilder.toString(),"\\\\"+c);
        }
        return string;
    }

    public static List stringToList(String string, String separator){
        String [] stringValues = string.split(separator);
        List<Object> values = new ArrayList<>();
        for (String value: stringValues) {
            values.add(value);
        }
        return values;
    }

    public static byte[] fileToByte(File file) throws IOException {
        byte[] buffer = new byte[(int) file.length()];
        InputStream ios = null;
        try {
            ios = new FileInputStream(file);
            if (ios.read(buffer) == -1)
                throw new IOException("EOF reached while trying to read the whole file");
        } finally {
            try {
                if (ios != null)
                    ios.close();
            } catch (IOException e) {
                throw e;
            }
        }
        return buffer;
    }

    public static String instantToString1(Instant instant){
        return Constants.DATE_TIME_FORMATTER_1.format(instant);
    }

    public static BigDecimal roundTwoDecimals(BigDecimal value){
        return value.setScale(2, RoundingMode.HALF_UP);
    }

    public static Instant xmlGregorianCalendarToInstant(XMLGregorianCalendar xmlGregorianCalendar ){
        return xmlGregorianCalendar.toGregorianCalendar()
            .toZonedDateTime()
            .toInstant();
    }

}
