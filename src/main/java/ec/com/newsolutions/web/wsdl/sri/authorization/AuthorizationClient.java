package ec.com.newsolutions.web.wsdl.sri.authorization;

import ec.com.newsolutions.config.ApplicationProperties;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;

public class AuthorizationClient extends WebServiceGatewaySupport {

    private final ApplicationProperties applicationProperties;

    public AuthorizationClient(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }
    public AutorizacionComprobanteResponse getAuthorizationResponse(Object request) {
        JAXBElement res = (JAXBElement) getWebServiceTemplate().marshalSendAndReceive(applicationProperties.getUrls().getAuthorization(), request);
        return (AutorizacionComprobanteResponse) res.getValue();

    }
}
