package ec.com.newsolutions.web.wsdl.sri;

import ec.com.newsolutions.config.ApplicationProperties;
import ec.com.newsolutions.web.wsdl.sri.authorization.AuthorizationClient;
import ec.com.newsolutions.web.wsdl.sri.reception.ReceptionClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;

@Configuration
public class MarshallConfig {

    private final ApplicationProperties applicationProperties;

    public MarshallConfig(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    @Bean
    public Jaxb2Marshaller marshaller()  {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPaths("ec.com.newsolutions.web.wsdl.sri.reception","ec.com.newsolutions.web.wsdl.sri.authorization");
        return marshaller;
    }
    @Bean
    public ReceptionClient receptionConnector(Jaxb2Marshaller marshaller) {
        ReceptionClient client = new ReceptionClient(applicationProperties);
        client.setDefaultUri(applicationProperties.getElectronicDocuments().getUrls().getReception());
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

    @Bean
    public AuthorizationClient authorizationConnector(Jaxb2Marshaller marshaller) {
        AuthorizationClient client = new AuthorizationClient(applicationProperties);
        client.setDefaultUri(applicationProperties.getElectronicDocuments().getUrls().getAuthorization());
        client.setMarshaller(marshaller);
        client.setUnmarshaller(marshaller);
        return client;
    }

}
