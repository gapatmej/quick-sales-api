package ec.com.newsolutions.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Properties specific to Quick Sales API.
 * <p>
 * Properties are configured in the {@code application.yml} file.
 * See {@link io.github.jhipster.config.JHipsterProperties} for a good example.
 */
@ConfigurationProperties(prefix = "application", ignoreUnknownFields = false)
public class ApplicationProperties {

    private ElectronicDocuments electronicDocuments = new ElectronicDocuments();

    public class ElectronicDocuments {
        private String path;

        public String getPath() {
            return path;
        }
        public void setPath(String path) {
            this.path = path;
        }

    }

    public ElectronicDocuments getElectronicDocuments() {
        return electronicDocuments;
    }

    public void setElectronicDocuments(ElectronicDocuments electronicDocuments) {
        this.electronicDocuments = electronicDocuments;
    }
}
