package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.service.DocumentElectronicService;
import ec.com.newsolutions.service.SignatureXAdES_BES;
import ec.com.newsolutions.xml.jaxb.sri.InvoiceClientJaxb;
import ec.com.newsolutions.xml.jaxb.sri.TributaryInformationJaxb;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;

@Service
@Transactional
public class DocumentElectronicServiceImpl implements DocumentElectronicService {

    private final Logger log = LoggerFactory.getLogger(InvoiceClientServiceImpl.class);

    private final SignatureXAdES_BES signatureXAdES_BES;

    public DocumentElectronicServiceImpl(SignatureXAdES_BES signatureXAdES_BES){
        this.signatureXAdES_BES = signatureXAdES_BES;
    }

    @Override
    public void generateXML(InvoiceClient invoiceClient) {
        try {
            InvoiceClientJaxb invoiceClientJaxb = new InvoiceClientJaxb();

            TributaryInformationJaxb tributaryInformationJaxb = new TributaryInformationJaxb();
            invoiceClientJaxb.setTributaryInformationJaxb(tributaryInformationJaxb);

            TributaryInformationJaxb tributaryInformationJaxb = new TributaryInformationJaxb();
            tributaryInformationJaxb.setAccessKey("bbbbbbbbbb");

            invoiceClientJaxb.setTributaryInformationJaxb(tributaryInformationJaxb);
            JAXBContext context = JAXBContext.newInstance(InvoiceClientJaxb.class);

            Marshaller m = context.createMarshaller();
            m.marshal(invoiceClientJaxb, new File("D:\\electronicDocuments\\Invoice\\issued\\pruebas.xml"));


            this.signatureXAdES_BES.execute();


        }catch (Exception e){
            log.debug(e.getMessage());
        }
    }

    public void firmar() {
     /*   SignatureXAdES_BESImpl xades = new SignatureXAdES_BESImpl(this);
        xades.execute();*/
    }
}
