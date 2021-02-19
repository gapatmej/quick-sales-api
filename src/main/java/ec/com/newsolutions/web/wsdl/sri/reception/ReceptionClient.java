package ec.com.newsolutions.web.wsdl.sri.reception;

import ec.com.newsolutions.config.ApplicationProperties;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import javax.xml.bind.JAXBElement;

public class ReceptionClient extends WebServiceGatewaySupport  {

    private final ApplicationProperties applicationProperties;

    public ReceptionClient(ApplicationProperties applicationProperties) {
        this.applicationProperties = applicationProperties;
    }

    public ValidarComprobanteResponse getReceptionResponse( Object request) {
        JAXBElement res = (JAXBElement) getWebServiceTemplate().marshalSendAndReceive(applicationProperties.getUrls().getReception(), request);
        return (ValidarComprobanteResponse) res.getValue();
    }
}
