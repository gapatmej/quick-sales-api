package ec.com.newsolutions.repository.specification;
import org.springframework.data.jpa.domain.Specification;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilsSpecification {

    public static <T> Specification<T> getSpecification(String search){
        SpecificationsBuilder builder = new SpecificationsBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|=|<|>|>=|<=)([\\w\\s]+?),");
        Matcher matcher = pattern.matcher(search + ",");
        while (matcher.find()) {
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));
        }
        return builder.build();
    }

}
