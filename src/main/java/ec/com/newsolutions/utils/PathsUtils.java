package ec.com.newsolutions.utils;

import ec.com.newsolutions.config.ApplicationProperties;
import ec.com.newsolutions.domain.enumeration.ReportsEnum;
import org.springframework.stereotype.Component;

@Component
public class PathsUtils {

    private static ApplicationProperties applicationProperties;

    public static void setApplicationProperties(ApplicationProperties applicationProperties) {
        PathsUtils.applicationProperties = applicationProperties;
    }

    public static String getReportsPathWithReportName(Long organizationId, ReportsEnum reportsEnum) {
        StringBuilder stringBuilder = new StringBuilder(applicationProperties.getPaths().getMain())
            .append(Utils.DIRECTORY_SEPARATOR)
            .append(String.format("%d", organizationId))
            .append(applicationProperties.getPaths().getReports().getMain())
            .append(Utils.DIRECTORY_SEPARATOR)
            .append(reportsEnum.jasperName());

        return stringBuilder.toString();
    }

    public static String getResourcesPathWithFileName(Long organizationId, String fileName) {
        StringBuilder stringBuilder = new StringBuilder(applicationProperties.getPaths().getMain())
            .append(Utils.DIRECTORY_SEPARATOR)
            .append(String.format("%d", organizationId))
            .append(applicationProperties.getPaths().getResources().getMain())
            .append(Utils.DIRECTORY_SEPARATOR)
            .append(fileName);

        return stringBuilder.toString();
    }

}
