package ec.com.newsolutions.repository.specification;
import ec.com.newsolutions.domain.Organization;
import ec.com.newsolutions.repository.enumeration.QueryOperationEnum;
import ec.com.newsolutions.security.SecurityUtils;
import ec.com.newsolutions.security.jwt.TokenProvider;
import ec.com.newsolutions.service.dto.OrganizationDTO;
import ec.com.newsolutions.service.dto.WorkspaceDTO;
import ec.com.newsolutions.utils.Utils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UtilsSpecification {

    private static TokenProvider tokenProvider;

    public static void setTokenProvider(TokenProvider tokenProvider) {
        UtilsSpecification.tokenProvider = tokenProvider;
    }

    public static <T> Specification<T> getSpecification(String search, Boolean validateSearch){
        SpecificationsBuilder builder = new SpecificationsBuilder();
        StringBuilder regex = new StringBuilder();
        regex.append("(\\"+QueryOperationEnum.OR.value()+")?");
        regex.append("([\\w\\.]+?)(");
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
            builder.with(matcher.group(1),matcher.group(2), matcher.group(3), matcher.group(4));
        }

        if(validateSearch && builder.size() == 0){
            builder.with(null,"1",QueryOperationEnum.NOT_DATA.value(), "1");
        }

        return builder.build();
    }

    public static <T> Specification<T> getSpecificationWithWorkspace(String search){
        Optional<Long> organizationId =  SecurityUtils.getCurrentWorkspace().map(WorkspaceDTO::getOrganization).map(OrganizationDTO::getId);
        if(StringUtils.isEmpty(search) ){
            search = "search=organization.id="+organizationId.get();
        }else{
            search += ",organization.id="+organizationId.get();
        }
        return UtilsSpecification.getSpecification(search,false);
    }

    public static <T> Specification<T> getSpecificationWithoutWorkspaceAndValidateSearch(String search){
        return UtilsSpecification.getSpecification(search,true);
    }

}
