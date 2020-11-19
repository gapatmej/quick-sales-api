package ec.com.newsolutions.repository.specification;
import ec.com.newsolutions.repository.enumeration.QueryOperationEnum;
import ec.com.newsolutions.utils.Utils;
import io.micrometer.core.instrument.util.StringEscapeUtils;
import org.springframework.data.jpa.domain.Specification;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilsSpecification {

    public static <T> Specification<T> getSpecification(String search){
        SpecificationsBuilder builder = new SpecificationsBuilder();
        StringBuilder regex = new StringBuilder();
        regex.append("(\\w+?)(");
        regex.append(QueryOperationEnum.LIKE.value()).append("|");
        regex.append(QueryOperationEnum.EQUAL.value()).append("|");
        regex.append(QueryOperationEnum.NOT_EQUAL.value()).append("|");
        regex.append(QueryOperationEnum.LESS_THAN.value()).append("|");
        regex.append(QueryOperationEnum.LESS_OR_EQUAL.value()).append("|");
        regex.append(QueryOperationEnum.GREATER_THAN.value()).append("|");
        regex.append(QueryOperationEnum.GREATER_OR_EQUAL.value()).append("|");
        regex.append(Utils.escapeCharacters(QueryOperationEnum.IN.value(),'[',']')).append("|");
        regex.append(Utils.escapeCharacters(QueryOperationEnum.NOT_IN.value(),'[',']'));
        regex.append(")");
        regex.append("([\\w\\s").append(QueryOperationEnum.IN_SEPARATOR.value()).append("]+?),");

        Pattern pattern = Pattern.compile(regex.toString());
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        return builder.build();
    }

}
