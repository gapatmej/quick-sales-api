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
        private Paths paths = new Paths();
        private Urls urls = new Urls();

        public Paths getPaths() {
            return paths;
        }

        public void setPaths(Paths paths) {
            this.paths = paths;
        }

        public Urls getUrls() {
            return urls;
        }

        public void setUrls(Urls urls) {
            this.urls = urls;
        }

        public class Paths {
            private String main;
            private String certificate;
            private Documents documents = new Documents();
            private String xml;
            private String signed;

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }

            public String getCertificate() {
                return certificate;
            }

            public void setCertificate(String certificate) {
                this.certificate = certificate;
            }

            public Documents getDocuments() {
                return documents;
            }

            public void setDocuments(Documents documents) {
                this.documents = documents;
            }

            public String getXml() {
                return xml;
            }

            public void setXml(String xml) {
                this.xml = xml;
            }

            public String getSigned() {
                return signed;
            }

            public void setSigned(String signed) {
                this.signed = signed;
            }

            public class Documents {
                private String invoices;

                public String getInvoices() {
                    return invoices;
                }
                public void setInvoices(String invoices) {
                    this.invoices = invoices;
                }
            }
        }

        public class Urls {
            private String reception;
            private String authorization;

            public String getReception() {
                return reception;
            }

            public void setReception(String reception) {
                this.reception = reception;
            }

            public String getAuthorization() {
                return authorization;
            }

            public void setAuthorization(String authorization) {
                this.authorization = authorization;
            }
        }
    }


    public ElectronicDocuments getElectronicDocuments() {
        return electronicDocuments;
    }

    public void setElectronicDocuments(ElectronicDocuments electronicDocuments) {
        this.electronicDocuments = electronicDocuments;
    }
}
