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

    private Urls urls = new Urls();
    private Paths paths = new Paths();

    public Urls getUrls() {
        return urls;
    }

    public void setUrls(Urls urls) {
        this.urls = urls;
    }

    public Paths getPaths() {
        return paths;
    }

    public void setPaths(Paths paths) {
        this.paths = paths;
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

    public class Paths {
        private String main;
        private ElectronicDocuments electronicDocuments = new ElectronicDocuments();
        private Certificate certificate = new Certificate();
        private Reports reports = new Reports();
        private Resources resources = new Resources();

        public String getMain() {
            return main;
        }

        public void setMain(String main) {
            this.main = main;
        }

        public Resources getResources() {
            return resources;
        }

        public void setResources(Resources resources) {
            this.resources = resources;
        }

        public ElectronicDocuments getElectronicDocuments() {
            return electronicDocuments;
        }

        public void setElectronicDocuments(ElectronicDocuments electronicDocuments) {
            this.electronicDocuments = electronicDocuments;
        }

        public Certificate getCertificate() {
            return certificate;
        }

        public void setCertificate(Certificate certificate) {
            this.certificate = certificate;
        }

        public Reports getReports() {
            return reports;
        }

        public void setReports(Reports reports) {
            this.reports = reports;
        }

        public class ElectronicDocuments {
            private String main;
            private Documents documents = new Documents();
            private String xml;
            private String signed;
            private String authorized;
            private String authorizedPdf;

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
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

            public String getAuthorized() {
                return authorized;
            }

            public void setAuthorized(String authorized) {
                this.authorized = authorized;
            }

            public String getAuthorizedPdf() {
                return authorizedPdf;
            }

            public void setAuthorizedPdf(String authorizedPdf) {
                this.authorizedPdf = authorizedPdf;
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

        public class Certificate {
            private String main;

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }

        }

        public class Reports {
            private String main;

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }

        }

        public class Resources {
            private String main;

            public String getMain() {
                return main;
            }

            public void setMain(String main) {
                this.main = main;
            }

        }
    }


}
