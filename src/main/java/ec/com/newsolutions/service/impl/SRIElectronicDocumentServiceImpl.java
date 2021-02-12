package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.config.ApplicationProperties;
import ec.com.newsolutions.domain.AdditionalInformation;
import ec.com.newsolutions.domain.DetailInvoiceClient;
import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.domain.Payment;
import ec.com.newsolutions.domain.SriMessage;
import ec.com.newsolutions.domain.TaxDetailInvoice;
import ec.com.newsolutions.domain.TaxInvoice;
import ec.com.newsolutions.domain.TributaryDocument;
import ec.com.newsolutions.domain.enumeration.SRIDocumentStateEnum;
import ec.com.newsolutions.service.ElectronicDocumentService;
import ec.com.newsolutions.service.SRIElectronicDocumentService;
import ec.com.newsolutions.service.SignatureXAdES;
import ec.com.newsolutions.service.SriMessageService;
import ec.com.newsolutions.service.errors.ElectronicDocumentException;
import ec.com.newsolutions.service.errors.enumeration.ProccessElectronicDocument;
import ec.com.newsolutions.utils.Utils;
import ec.com.newsolutions.utils.electronicdocuments.ElectronicDocumentsUtils;
import ec.com.newsolutions.utils.electronicdocuments.Signature;
import ec.com.newsolutions.web.wsdl.sri.authorization.AuthorizationClient;
import ec.com.newsolutions.web.wsdl.sri.authorization.Autorizacion;
import ec.com.newsolutions.web.wsdl.sri.authorization.AutorizacionComprobante;
import ec.com.newsolutions.web.wsdl.sri.authorization.AutorizacionComprobanteResponse;
import ec.com.newsolutions.web.wsdl.sri.authorization.RespuestaComprobante;
import ec.com.newsolutions.web.wsdl.sri.reception.Comprobante;
import ec.com.newsolutions.web.wsdl.sri.reception.ReceptionClient;
import ec.com.newsolutions.web.wsdl.sri.reception.RespuestaSolicitud;
import ec.com.newsolutions.web.wsdl.sri.reception.ValidarComprobante;
import ec.com.newsolutions.web.wsdl.sri.reception.ValidarComprobanteResponse;
import ec.com.newsolutions.xml.jaxb.sri.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.File;
import java.util.HashSet;
import java.util.Set;

@Service
@Transactional
public class SRIElectronicDocumentServiceImpl extends AbstractService implements SRIElectronicDocumentService {

    private final SignatureXAdES signatureXAdES;
    private final ApplicationProperties applicationProperties;
    private final ReceptionClient receptionClient;
    private final SriMessageService sriMessageService;
    private final ElectronicDocumentService electronicDocumentService;
    private final AuthorizationClient authorizationClient;

    public SRIElectronicDocumentServiceImpl(SignatureXAdES signatureXAdES, ApplicationProperties applicationProperties, ReceptionClient receptionClient,
                                            SriMessageService sriMessageService, ElectronicDocumentService electronicDocumentService, AuthorizationClient authorizationClient) {
        super(SRIElectronicDocumentServiceImpl.class);
        this.signatureXAdES = signatureXAdES;
        this.applicationProperties = applicationProperties;
        this.receptionClient = receptionClient;
        this.sriMessageService = sriMessageService;
        this.electronicDocumentService = electronicDocumentService;
        this.authorizationClient = authorizationClient;
    }

    @Override
    public void sendInvoiceToSRI(InvoiceClient invoiceClient) {
        try{
            generateInvoiceClientXML(invoiceClient);
            sendToSRI(invoiceClient);
        }catch(ElectronicDocumentException electronicDocumentException){
            log.error(electronicDocumentException.getMessage());
        }
    }

    private void sendToSRI(TributaryDocument tributaryDocument){
        Signature signature = new Signature(tributaryDocument);
        sign(signature);
        receive(tributaryDocument);
        authorize(tributaryDocument);
    }

    @Override
    public void generateInvoiceClientXML(InvoiceClient invoiceClient) throws ElectronicDocumentException {
        try {
            InvoiceClientJaxb invoiceClientJaxb = new InvoiceClientJaxb();
            TributaryInformationJaxb tributaryInformationJaxb = new TributaryInformationJaxb();
            tributaryInformationJaxb.setEnvironment(invoiceClient.getElectronicDocument().getSriEnvironment().code());
            tributaryInformationJaxb.setEmissionType(invoiceClient.getElectronicDocument().getEmissionType().code());
            tributaryInformationJaxb.setBusinessName(invoiceClient.getOrganization().getBusinessName());
            tributaryInformationJaxb.setTradename(invoiceClient.getOrganization().getTradename());
            tributaryInformationJaxb.setIdentification(invoiceClient.getOrganization().getIdentification());
            tributaryInformationJaxb.setAccessKey(invoiceClient.getElectronicDocument().getAccessKey());
            tributaryInformationJaxb.setCodeDocument(invoiceClient.getElectronicDocument().getReceiptType().code());
            tributaryInformationJaxb.setEstablishmentCode(invoiceClient.getEstablishmentCode());
            tributaryInformationJaxb.setEmissionPointCode(invoiceClient.getEmissionPointCode());
            tributaryInformationJaxb.setSequence(String.format("%09d", invoiceClient.getSequence()));
            tributaryInformationJaxb.setMatrizAddress(invoiceClient.getOrganization().getAddress());
            invoiceClientJaxb.setTributaryInformationJaxb(tributaryInformationJaxb);

            InvoiceInformationJaxb invoiceInformationJaxb = new InvoiceInformationJaxb();
            invoiceInformationJaxb.setDateIssue(Utils.instantToString1(invoiceClient.getDateIssue()));
            invoiceInformationJaxb.setEstablishmentAddress(invoiceClient.getOrganization().getAddress());
            invoiceInformationJaxb.setSpecialTaxpayer(String.valueOf(invoiceClient.getOrganization().getSpecialTaxpayerNumber()));
            invoiceInformationJaxb.setObligedAccounting(invoiceClient.getOrganization().getKeepAccounting() ? "SI" : "NO");
            invoiceInformationJaxb.setBuyerIdentificationType(invoiceClient.getIdentificationType().code());
            invoiceInformationJaxb.setBuyerBusinessName(invoiceClient.getBusinessName());
            invoiceInformationJaxb.setBuyerIdentification(invoiceClient.getIdentification());
            invoiceInformationJaxb.setBuyerAddress(invoiceClient.getAddress());
            invoiceInformationJaxb.setTotalWithoutTaxes(invoiceClient.getTotalWithoutTax());
            invoiceInformationJaxb.setTotalDiscount(invoiceClient.getTotalDiscount());

            TotalWithTaxesJaxb totalWithTaxesJaxb = new TotalWithTaxesJaxb();
            for (TaxInvoice taxInvoice : invoiceClient.getTaxesInvoice()) {
                TotalTaxJaxb totalTaxJaxb = new TotalTaxJaxb();
                totalTaxJaxb.setCode(taxInvoice.getCode());
                totalTaxJaxb.setPercentageCode(Integer.valueOf(taxInvoice.getPercentageCode()));
                totalTaxJaxb.setTaxBase(taxInvoice.getTaxBase());
                totalTaxJaxb.setValue(Utils.roundTwoDecimals(taxInvoice.getAmount()));
                totalWithTaxesJaxb.getTotalTaxJaxb().add(totalTaxJaxb);
            }
            invoiceInformationJaxb.setTotalWithTaxesJaxb(totalWithTaxesJaxb);

            invoiceInformationJaxb.setTip(invoiceClient.getTip());
            invoiceInformationJaxb.setTotal(invoiceClient.getTotal());
            invoiceInformationJaxb.setCurrency(invoiceClient.getCurrency().toString());

            PaymentsJaxb paymentsJaxb = new PaymentsJaxb();
            for (Payment payment : invoiceClient.getPayments()) {
                PaymentJaxb paymentJaxb = new PaymentJaxb();
                paymentJaxb.setPayWay("18");
                paymentJaxb.setTotal(payment.getAmount());
                paymentJaxb.setTimeUnit(payment.getTimeUnit().value());
                paymentsJaxb.getPayment().add(paymentJaxb);
            }
            invoiceInformationJaxb.setPaymentsJaxb(paymentsJaxb);
            invoiceClientJaxb.setInvoiceInformationJaxb(invoiceInformationJaxb);


            DetailsJaxb detailsJaxb = new DetailsJaxb();
            for (DetailInvoiceClient detailInvoice : invoiceClient.getDetailsInvoiceClient()) {
                DetailJaxb detailJaxb = new DetailJaxb();
                detailsJaxb.getDetailJaxb().add(detailJaxb);

                detailJaxb.setMainCode(detailInvoice.getMainCode());
                detailJaxb.setAuxiliaryCode(detailInvoice.getAuxiliaryCode());
                detailJaxb.setDescription(detailInvoice.getDescription());
                detailJaxb.setQuantity(detailInvoice.getQuantity());
                detailJaxb.setUnitPrice(detailInvoice.getUnitPrice());
                detailJaxb.setDiscount(detailInvoice.getDiscount());
                detailJaxb.setTotalPriceWithoutTax(detailInvoice.getTotal());

                TaxesJaxb taxesJaxb = new TaxesJaxb();
                for (TaxDetailInvoice taxDetailInvoice : detailInvoice.getTaxesDetailInvoice()) {
                    TaxJaxb taxJaxb = new TaxJaxb();
                    taxJaxb.setCode(taxDetailInvoice.getCode());
                    taxJaxb.setPercentagecode(taxDetailInvoice.getPercentageCode());
                    taxJaxb.setRate((int) Math.round(taxDetailInvoice.getRate()));
                    taxJaxb.setTaxBase(taxDetailInvoice.getTaxBase());
                    taxJaxb.setValue(Utils.roundTwoDecimals(taxDetailInvoice.getAmount()));

                    taxesJaxb.getTaxJaxb().add(taxJaxb);
                }
                detailJaxb.setTaxesJaxb(taxesJaxb);
            }
            invoiceClientJaxb.setDetailsJaxb(detailsJaxb);

            AdditionalInformation ad = new AdditionalInformation();
            ad.setAdditionalField("nombre");
            ad.setValue("aaa");
            Set<AdditionalInformation> lis = new HashSet<>();
            lis.add(ad);
            invoiceClient.setAdditionalsInformation(lis);

            AdditionalsInformationJaxb additionalsInformationJaxb = new AdditionalsInformationJaxb();
            for (AdditionalInformation additionalInformation : invoiceClient.getAdditionalsInformation()) {
                AdditionalFieldJaxb additionalFieldJaxb = new AdditionalFieldJaxb();
                additionalFieldJaxb.setName(additionalInformation.getAdditionalField());
                additionalFieldJaxb.setValue(additionalInformation.getValue());
                additionalsInformationJaxb.getAdditionalFieldJaxbs().add(additionalFieldJaxb);
            }
            invoiceClientJaxb.setAdditionalsInformationJaxb(additionalsInformationJaxb);

            JAXBContext context = JAXBContext.newInstance(InvoiceClientJaxb.class);
            Marshaller m = context.createMarshaller();
            m.marshal(invoiceClientJaxb, new File(ElectronicDocumentsUtils.getXMlPathWithAccessKey(invoiceClient)));

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ElectronicDocumentException(ProccessElectronicDocument.GENERATE_XML, invoiceClient.getElectronicDocument().getAccessKey(), e.getMessage());
        }

    }

    @Override
    public void sign(Signature signature) throws ElectronicDocumentException {
        try {
            this.signatureXAdES.execute(signature);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ElectronicDocumentException(ProccessElectronicDocument.SIGN, signature.getSignedPath(), e.getMessage());
        }

    }

    @Override
    public void receive(TributaryDocument tributaryDocument) throws ElectronicDocumentException {
        try {
            ec.com.newsolutions.web.wsdl.sri.reception.ObjectFactory objectFactoryReception = new ec.com.newsolutions.web.wsdl.sri.reception.ObjectFactory();
            ValidarComprobante validarComprobante = new ValidarComprobante();
            File file = new File(ElectronicDocumentsUtils.getSignedPathWithAccessKey(tributaryDocument));
            validarComprobante.setXml(Utils.fileToByte(file));

            ValidarComprobanteResponse response = receptionClient.getReceptionResponse(objectFactoryReception.createValidarComprobante(validarComprobante));
            String state = response.getRespuestaRecepcionComprobante().getEstado();
            RespuestaSolicitud.Comprobantes documents = response.getRespuestaRecepcionComprobante().getComprobantes();
            String errorMessage = "";
            if (documents != null && documents.getComprobante().size() > 0) {
                Comprobante document = documents.getComprobante().get(0);
                if (document != null && document.getMensajes() != null) {
                    for (ec.com.newsolutions.web.wsdl.sri.reception.Mensaje mensaje : document.getMensajes().getMensaje()) {
                        SriMessage sriMessage = new SriMessage();
                        sriMessage.setIdentificator(Integer.parseInt(mensaje.getIdentificador()));
                        sriMessage.setAdditionalInformation(sriMessage.getAdditionalInformation());
                        sriMessage.setMessage(sriMessage.getMessage());
                        sriMessage.setType(sriMessage.getType());
                        sriMessage.setElectronicDocument(tributaryDocument.getElectronicDocument());
                        sriMessageService.save(sriMessage);
                        errorMessage = sriMessage.getMessage();
                    }
                }
            }

            electronicDocumentService.updateSriDocumentState(SRIDocumentStateEnum.valueOf(state), tributaryDocument.getElectronicDocument());

            if(!SRIDocumentStateEnum.RECEIVED.state().equals(state)){
                throw new ElectronicDocumentException(ProccessElectronicDocument.RECEPTION, tributaryDocument.getElectronicDocument().getAccessKey(),errorMessage);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ElectronicDocumentException(ProccessElectronicDocument.RECEPTION, tributaryDocument.getElectronicDocument().getAccessKey(), e.getMessage());
        }
    }

    @Override
    public void authorize(TributaryDocument tributaryDocument) throws ElectronicDocumentException {

        ec.com.newsolutions.web.wsdl.sri.authorization.ObjectFactory objectFactory = new ec.com.newsolutions.web.wsdl.sri.authorization.ObjectFactory();
        AutorizacionComprobante autorizacionComprobante = new AutorizacionComprobante();
        autorizacionComprobante.setClaveAccesoComprobante(tributaryDocument.getElectronicDocument().getAccessKey());

        AutorizacionComprobanteResponse response = authorizationClient.getAuthorizationResponse(objectFactory.createAutorizacionComprobante(autorizacionComprobante));
        RespuestaComprobante.Autorizaciones authorizations = response.getRespuestaAutorizacionComprobante().getAutorizaciones();

        if (authorizations != null && authorizations.getAutorizacion().size() > 0) {
            Autorizacion authorization = authorizations.getAutorizacion().get(0);
            String errorMessage = "";
            if (authorization != null && authorization.getMensajes() != null) {
                for (ec.com.newsolutions.web.wsdl.sri.authorization.Mensaje mensaje : authorization.getMensajes().getMensaje()) {
                    SriMessage sriMessage = new SriMessage();
                    sriMessage.setIdentificator(Integer.parseInt(mensaje.getIdentificador()));
                    sriMessage.setAdditionalInformation(sriMessage.getAdditionalInformation());
                    sriMessage.setMessage(sriMessage.getMessage());
                    sriMessage.setType(sriMessage.getType());
                    sriMessage.setElectronicDocument(tributaryDocument.getElectronicDocument());
                    sriMessageService.save(sriMessage);
                    errorMessage = sriMessage.getMessage();
                }
            }
            if(SRIDocumentStateEnum.AUTHORIZED.state().equals(authorization.getEstado())){
                //invoiceClientService.updateSriDocumentState(SRIDocumentStateEnum.valueOf(state), invoiceClient);
            }else{
                throw new ElectronicDocumentException(ProccessElectronicDocument.AUTHORIZATION, tributaryDocument.getElectronicDocument().getAccessKey(),errorMessage);

            }
        }

    }


}
