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
import ec.com.newsolutions.service.reports.ReportInvoiceClientService;
import ec.com.newsolutions.utils.Utils;
import ec.com.newsolutions.utils.electronicdocuments.ElectronicDocumentsUtils;
import ec.com.newsolutions.utils.electronicdocuments.Signature;
import ec.com.newsolutions.web.wsdl.sri.authorization.Autorizacion;
import ec.com.newsolutions.web.wsdl.sri.authorization.AutorizacionComprobante;
import ec.com.newsolutions.web.wsdl.sri.authorization.RespuestaComprobante;
import ec.com.newsolutions.web.wsdl.sri.reception.Comprobante;
import ec.com.newsolutions.web.wsdl.sri.reception.ReceptionClient;
import ec.com.newsolutions.web.wsdl.sri.reception.RespuestaSolicitud;
import ec.com.newsolutions.web.wsdl.sri.reception.ValidarComprobante;
import ec.com.newsolutions.web.wsdl.sri.reception.ValidarComprobanteResponse;
import ec.com.newsolutions.xml.jaxb.sri.AdditionalFieldJaxb;
import ec.com.newsolutions.xml.jaxb.sri.AdditionalsInformationJaxb;
import ec.com.newsolutions.xml.jaxb.sri.DetailJaxb;
import ec.com.newsolutions.xml.jaxb.sri.DetailsJaxb;
import ec.com.newsolutions.xml.jaxb.sri.InvoiceClientJaxb;
import ec.com.newsolutions.xml.jaxb.sri.InvoiceInformationJaxb;
import ec.com.newsolutions.xml.jaxb.sri.PaymentJaxb;
import ec.com.newsolutions.xml.jaxb.sri.PaymentsJaxb;
import ec.com.newsolutions.xml.jaxb.sri.TaxJaxb;
import ec.com.newsolutions.xml.jaxb.sri.TaxesJaxb;
import ec.com.newsolutions.xml.jaxb.sri.TotalTaxJaxb;
import ec.com.newsolutions.xml.jaxb.sri.TotalWithTaxesJaxb;
import ec.com.newsolutions.xml.jaxb.sri.TributaryInformationJaxb;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.io.File;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
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
    private final ReportInvoiceClientService reportInvoiceClientService;

    public SRIElectronicDocumentServiceImpl(SignatureXAdES signatureXAdES, ApplicationProperties applicationProperties, ReceptionClient receptionClient,
                                            SriMessageService sriMessageService, ElectronicDocumentService electronicDocumentService,
                                            ReportInvoiceClientService reportInvoiceClientService) {
        super(SRIElectronicDocumentServiceImpl.class);
        this.signatureXAdES = signatureXAdES;
        this.applicationProperties = applicationProperties;
        this.receptionClient = receptionClient;
        this.sriMessageService = sriMessageService;
        this.electronicDocumentService = electronicDocumentService;
        this.reportInvoiceClientService = reportInvoiceClientService;
    }

    @Override
    public void sendInvoiceToSRI(InvoiceClient invoiceClient) {
        try {
            generateInvoiceClientXML(invoiceClient);
            sendToSRI(invoiceClient);
        } catch (ElectronicDocumentException electronicDocumentException) {
            log.error(electronicDocumentException.getMessage());
        }
    }

    private void sendToSRI(TributaryDocument tributaryDocument) {
        Signature signature = new Signature(tributaryDocument);
        sign(signature);
        //  receive(tributaryDocument);
        Autorizacion autorizacion = authorize(tributaryDocument);
        ElectronicDocumentsUtils.generateAuthorizedDocumentRide(tributaryDocument, autorizacion);
        generateAuthorizedDocumentPdf(tributaryDocument);
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

            if (!SRIDocumentStateEnum.RECEIVED.state().equals(state)) {
                throw new ElectronicDocumentException(ProccessElectronicDocument.RECEPTION, tributaryDocument.getElectronicDocument().getAccessKey(), errorMessage);
            }

        } catch (Exception e) {
            log.error(e.getMessage());
            throw new ElectronicDocumentException(ProccessElectronicDocument.RECEPTION, tributaryDocument.getElectronicDocument().getAccessKey(), e.getMessage());
        }
    }
/*
    @Override
    public Autorizacion authorize(TributaryDocument tributaryDocument) throws ElectronicDocumentException {
        Autorizacion autorizacion = null;
        ec.com.newsolutions.web.wsdl.sri.authorization.ObjectFactory objectFactory = new ec.com.newsolutions.web.wsdl.sri.authorization.ObjectFactory();
        AutorizacionComprobante autorizacionComprobante = new AutorizacionComprobante();
        autorizacionComprobante.setClaveAccesoComprobante(tributaryDocument.getElectronicDocument().getAccessKey());

        AutorizacionComprobanteResponse response = authorizationClient.getAuthorizationResponse(objectFactory.createAutorizacionComprobante(autorizacionComprobante));
        RespuestaComprobante.Autorizaciones authorizations = response.getRespuestaAutorizacionComprobante().getAutorizaciones();

        if (authorizations != null && authorizations.getAutorizacion().size() > 0) {
            autorizacion = authorizations.getAutorizacion().get(0);
            String errorMessage = "";
            if (autorizacion != null && autorizacion.getMensajes() != null) {
                for (ec.com.newsolutions.web.wsdl.sri.authorization.Mensaje mensaje : autorizacion.getMensajes().getMensaje()) {
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
            if(SRIDocumentStateEnum.AUTHORIZED.state().equals(autorizacion.getEstado())){
                electronicDocumentService.updateSriAuthorizedFields(tributaryDocument.getElectronicDocument(),
                    SRIDocumentStateEnum.AUTHORIZED, Utils.xmlGregorianCalendarToInstant(autorizacion.getFechaAutorizacion()));
            }else{
                throw new ElectronicDocumentException(ProccessElectronicDocument.AUTHORIZATION, tributaryDocument.getElectronicDocument().getAccessKey(),errorMessage);
            }
        }
        return autorizacion;

    }*/

    @Override
    public Autorizacion authorize(TributaryDocument tributaryDocument) throws ElectronicDocumentException {
        try {
            Autorizacion autorizacion = null;
            ec.com.newsolutions.web.wsdl.sri.authorization.ObjectFactory objectFactory = new ec.com.newsolutions.web.wsdl.sri.authorization.ObjectFactory();
            AutorizacionComprobante autorizacionComprobante = new AutorizacionComprobante();
            autorizacionComprobante.setClaveAccesoComprobante(tributaryDocument.getElectronicDocument().getAccessKey());

            RespuestaComprobante.Autorizaciones authorizations = new RespuestaComprobante.Autorizaciones();
            Autorizacion authorizationTest = new Autorizacion();
            authorizationTest.setEstado("AUTORIZADO");
            authorizationTest.setNumeroAutorizacion("2612202001179206034600120340800001787335658032312");
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssXXX");
            Date date = format.parse("2020-12-27T07:08:12-05:00");

            GregorianCalendar cal = new GregorianCalendar();
            cal.setTime(date);

            XMLGregorianCalendar xmlGregCal = DatatypeFactory.newInstance().newXMLGregorianCalendar(cal);
            authorizationTest.setFechaAutorizacion(xmlGregCal);
            authorizationTest.setAmbiente("PRUEBAS");
            authorizationTest.setComprobante("<![CDATA[<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                "<factura id=\"comprobante\" version=\"1.1.0\">\n" +
                "    <infoTributaria>\n" +
                "        <ambiente>2</ambiente>\n" +
                "        <tipoEmision>1</tipoEmision>\n" +
                "        <razonSocial>CEVICHERIA EL MANGLAR DE LAS CONCHAS</razonSocial>\n" +
                "        <nombreComercial>CEVICHERIA EL MANGLAR DE LAS CONCHAS</nombreComercial>\n" +
                "        <ruc>1713890778001</ruc>\n" +
                "        <claveAcceso>0602202101171389077800120010020000586791234567811</claveAcceso>\n" +
                "        <codDoc>01</codDoc>\n" +
                "        <estab>001</estab>\n" +
                "        <ptoEmi>002</ptoEmi>\n" +
                "        <secuencial>000058679</secuencial>\n" +
                "        <dirMatriz>AV. DE LA PRENSA N46-62 Y LOGROÑO</dirMatriz>\n" +
                "    </infoTributaria>\n" +
                "    <infoFactura>\n" +
                "        <fechaEmision>06/02/2021</fechaEmision>\n" +
                "        <dirEstablecimiento>AV. DE LA PRENSA N46-62 Y LOGROÑO</dirEstablecimiento>\n" +
                "        <obligadoContabilidad>SI</obligadoContabilidad>\n" +
                "        <tipoIdentificacionComprador>05</tipoIdentificacionComprador>\n" +
                "        <razonSocialComprador>ANDRES PERALTA</razonSocialComprador>\n" +
                "        <identificacionComprador>1720238706</identificacionComprador>\n" +
                "        <direccionComprador>CARCELEN</direccionComprador>\n" +
                "        <totalSinImpuestos>14.29</totalSinImpuestos>\n" +
                "        <totalDescuento>0.00</totalDescuento>\n" +
                "        <totalConImpuestos>\n" +
                "            <totalImpuesto>\n" +
                "                <codigo>2</codigo>\n" +
                "                <codigoPorcentaje>2</codigoPorcentaje>\n" +
                "                <baseImponible>14.29</baseImponible>\n" +
                "                <tarifa>12</tarifa>\n" +
                "                <valor>1.71</valor>\n" +
                "            </totalImpuesto>\n" +
                "        </totalConImpuestos>\n" +
                "        <propina>0.00</propina>\n" +
                "        <importeTotal>16.00</importeTotal>\n" +
                "        <moneda>DOLAR</moneda>\n" +
                "        <pagos>\n" +
                "            <pago>\n" +
                "                <formaPago>01</formaPago>\n" +
                "                <total>16.00</total>\n" +
                "                <plazo>0</plazo>\n" +
                "                <unidadTiempo>DIAS</unidadTiempo>\n" +
                "            </pago>\n" +
                "        </pagos>\n" +
                "    </infoFactura>\n" +
                "    <detalles>\n" +
                "        <detalle>\n" +
                "            <codigoPrincipal>142</codigoPrincipal>\n" +
                "            <codigoAuxiliar>142</codigoAuxiliar>\n" +
                "            <descripcion>B. MENESTRA CON POLLO</descripcion>\n" +
                "            <cantidad>1</cantidad>\n" +
                "            <precioUnitario>4.4643</precioUnitario>\n" +
                "            <descuento>0.00</descuento>\n" +
                "            <precioTotalSinImpuesto>4.46</precioTotalSinImpuesto>\n" +
                "            <impuestos>\n" +
                "                <impuesto>\n" +
                "                    <codigo>2</codigo>\n" +
                "                    <codigoPorcentaje>2</codigoPorcentaje>\n" +
                "                    <tarifa>12</tarifa>\n" +
                "                    <baseImponible>4.46</baseImponible>\n" +
                "                    <valor>0.54</valor>\n" +
                "                </impuesto>\n" +
                "            </impuestos>\n" +
                "        </detalle>\n" +
                "        <detalle>\n" +
                "            <codigoPrincipal>144</codigoPrincipal>\n" +
                "            <codigoAuxiliar>144</codigoAuxiliar>\n" +
                "            <descripcion>D. MENESTRA MIXTA</descripcion>\n" +
                "            <cantidad>1</cantidad>\n" +
                "            <precioUnitario>7.37</precioUnitario>\n" +
                "            <descuento>0.00</descuento>\n" +
                "            <precioTotalSinImpuesto>7.37</precioTotalSinImpuesto>\n" +
                "            <impuestos>\n" +
                "                <impuesto>\n" +
                "                    <codigo>2</codigo>\n" +
                "                    <codigoPorcentaje>2</codigoPorcentaje>\n" +
                "                    <tarifa>12</tarifa>\n" +
                "                    <baseImponible>7.37</baseImponible>\n" +
                "                    <valor>0.88</valor>\n" +
                "                </impuesto>\n" +
                "            </impuestos>\n" +
                "        </detalle>\n" +
                "        <detalle>\n" +
                "            <codigoPrincipal>165</codigoPrincipal>\n" +
                "            <codigoAuxiliar>165</codigoAuxiliar>\n" +
                "            <descripcion>E. COLA PEQUEÑA</descripcion>\n" +
                "            <cantidad>1</cantidad>\n" +
                "            <precioUnitario>1.1161</precioUnitario>\n" +
                "            <descuento>0.00</descuento>\n" +
                "            <precioTotalSinImpuesto>1.12</precioTotalSinImpuesto>\n" +
                "            <impuestos>\n" +
                "                <impuesto>\n" +
                "                    <codigo>2</codigo>\n" +
                "                    <codigoPorcentaje>2</codigoPorcentaje>\n" +
                "                    <tarifa>12</tarifa>\n" +
                "                    <baseImponible>1.12</baseImponible>\n" +
                "                    <valor>0.13</valor>\n" +
                "                </impuesto>\n" +
                "            </impuestos>\n" +
                "        </detalle>\n" +
                "        <detalle>\n" +
                "            <codigoPrincipal>163</codigoPrincipal>\n" +
                "            <codigoAuxiliar>163</codigoAuxiliar>\n" +
                "            <descripcion>C. QUAKER VASO</descripcion>\n" +
                "            <cantidad>1</cantidad>\n" +
                "            <precioUnitario>1.3393</precioUnitario>\n" +
                "            <descuento>0.00</descuento>\n" +
                "            <precioTotalSinImpuesto>1.34</precioTotalSinImpuesto>\n" +
                "            <impuestos>\n" +
                "                <impuesto>\n" +
                "                    <codigo>2</codigo>\n" +
                "                    <codigoPorcentaje>2</codigoPorcentaje>\n" +
                "                    <tarifa>12</tarifa>\n" +
                "                    <baseImponible>1.34</baseImponible>\n" +
                "                    <valor>0.16</valor>\n" +
                "                </impuesto>\n" +
                "            </impuestos>\n" +
                "        </detalle>\n" +
                "    </detalles>\n" +
                "    <infoAdicional>\n" +
                "        <campoAdicional nombre=\"Realizado por:\">EL EQUIPO MANGLAR</campoAdicional>\n" +
                "        <campoAdicional nombre=\"Fecha:\">06/02/2021 18:44:02</campoAdicional>\n" +
                "        <campoAdicional nombre=\"Email:\">gapatmej@gmail.com</campoAdicional>\n" +
                "    </infoAdicional>\n" +
                "<ds:Signature xmlns:ds=\"http://www.w3.org/2000/09/xmldsig#\" xmlns:etsi=\"http://uri.etsi.org/01903/v1.3.2#\" Id=\"Signature925142\">\n" +
                "<ds:SignedInfo Id=\"Signature-SignedInfo188510\">\n" +
                "<ds:CanonicalizationMethod Algorithm=\"http://www.w3.org/TR/2001/REC-xml-c14n-20010315\"></ds:CanonicalizationMethod>\n" +
                "<ds:SignatureMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#rsa-sha1\"></ds:SignatureMethod>\n" +
                "<ds:Reference Id=\"SignedPropertiesID383693\" Type=\"http://uri.etsi.org/01903#SignedProperties\" URI=\"#Signature925142-SignedProperties420748\">\n" +
                "<ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"></ds:DigestMethod>\n" +
                "<ds:DigestValue>8EMzF/a/KB/j/hv/WF7QyHV5yd4=</ds:DigestValue>\n" +
                "</ds:Reference>\n" +
                "<ds:Reference URI=\"#Certificate1463995\">\n" +
                "<ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"></ds:DigestMethod>\n" +
                "<ds:DigestValue>kkn4zTqHQ8Yv0/mEOOpEDzDcTIY=</ds:DigestValue>\n" +
                "</ds:Reference>\n" +
                "<ds:Reference Id=\"Reference-ID-23296\" URI=\"#comprobante\">\n" +
                "<ds:Transforms>\n" +
                "<ds:Transform Algorithm=\"http://www.w3.org/2000/09/xmldsig#enveloped-signature\"></ds:Transform>\n" +
                "</ds:Transforms>\n" +
                "<ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"></ds:DigestMethod>\n" +
                "<ds:DigestValue>SJ69cyIWxd+695hjP6yOpQfvSB0=</ds:DigestValue>\n" +
                "</ds:Reference>\n" +
                "</ds:SignedInfo>\n" +
                "<ds:SignatureValue Id=\"SignatureValue722179\">\n" +
                "q5tnuimRsRRQ9MXl+IFJfjg2TaVF9QXrmzOgVSDWiFTWjvC4/jyvze92oHXzDhBte1JA8a9y+enI\n" +
                "iKAYroo1NoC9kuYDeMCJCz9CD5nhWVknVnE2RO6Iml2dK3f3nGel3CEaECWVt+HQz+4tun+UmK66\n" +
                "1k4uJlutWhc1PWDQe1sgKWkRZQ/mLbqeelElLSn64iDwrsnxtP+/op7ld+vMabISaqoOHWkP9+Ym\n" +
                "JCRFj1mu8pObVk4E2qQ4Ctw1CnWR5Is45DFy/u0hg4VXbklxtiRK4s4p3MCBn/EJJf3q3bEtmdg2\n" +
                "0hMy4F/NYHjDSBn5ZwcodAiP14fRTItuzuedMQ==\n" +
                "</ds:SignatureValue>\n" +
                "<ds:KeyInfo Id=\"Certificate1463995\">\n" +
                "<ds:X509Data>\n" +
                "<ds:X509Certificate>\n" +
                "MIIJoDCCB4igAwIBAgIEW2ZvozANBgkqhkiG9w0BAQsFADCBoTELMAkGA1UEBhMCRUMxIjAgBgNV\n" +
                "BAoTGUJBTkNPIENFTlRSQUwgREVMIEVDVUFET1IxNzA1BgNVBAsTLkVOVElEQUQgREUgQ0VSVElG\n" +
                "SUNBQ0lPTiBERSBJTkZPUk1BQ0lPTi1FQ0lCQ0UxDjAMBgNVBAcTBVFVSVRPMSUwIwYDVQQDExxB\n" +
                "QyBCQU5DTyBDRU5UUkFMIERFTCBFQ1VBRE9SMB4XDTIwMTEyMDE4MDAzNVoXDTIyMTExMjE4MTc0\n" +
                "NlowgbUxCzAJBgNVBAYTAkVDMSIwIAYDVQQKExlCQU5DTyBDRU5UUkFMIERFTCBFQ1VBRE9SMTcw\n" +
                "NQYDVQQLEy5FTlRJREFEIERFIENFUlRJRklDQUNJT04gREUgSU5GT1JNQUNJT04tRUNJQkNFMQ4w\n" +
                "DAYDVQQHEwVRVUlUTzE5MBEGA1UEBRMKMDAwMDU2MTc4NzAkBgNVBAMTHUZSRUREWSBPU1dBTERP\n" +
                "IFJPTUFOIEdVRVJSRVJPMIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAzLTrvSyEXA5C\n" +
                "lcekFdCCLu2FCBO6caHgBDWAGOCnP9zs0G15CTgvWjaKIXhzJ9u0RcYrHEJzC/3d20htMimftORB\n" +
                "3bioreP1mdFL1ashWUCf0VlUWTUjFHiXY4xJMZnlT8oetp5M29EX8cP/M+UOlKt2v2r8b4Eq8S1k\n" +
                "4bzCQNgU9z2V/9FyfZ3JF7c4PoYyKhNkADxDkT/8UzgG38BnqwQk8CYrrGE4fk9Ugc3wWAImCVvm\n" +
                "EbDHjRk4AaSEpzMyJE9ex+K1soKCmI0eFfHTRG8F6A2MDSIAHh1Cm0sYIx9MZsLF2mIqwwwpRmGk\n" +
                "xw+GO9n0Fe5TjzupjOPuqjhNRQIDAQABo4IEyDCCBMQwCwYDVR0PBAQDAgeAMGYGA1UdIARfMF0w\n" +
                "WwYLKwYBBAGCqDsCAQEwTDBKBggrBgEFBQcCARY+aHR0cDovL3d3dy5lY2kuYmNlLmVjL3BvbGl0\n" +
                "aWNhLWNlcnRpZmljYWRvL3BlcnNvbmEtbmF0dXJhbC5wZGYwgZEGCCsGAQUFBwEBBIGEMIGBMD4G\n" +
                "CCsGAQUFBzABhjJodHRwOi8vb2NzcC5lY2kuYmNlLmVjL2VqYmNhL3B1YmxpY3dlYi9zdGF0dXMv\n" +
                "b2NzcDA/BggrBgEFBQcwAYYzaHR0cDovL29jc3AxLmVjaS5iY2UuZWMvZWpiY2EvcHVibGljd2Vi\n" +
                "L3N0YXR1cy9vY3NwMBoGCisGAQQBgqg7AwEEDBMKMTcxMzg5MDc3ODAeBgorBgEEAYKoOwMCBBAT\n" +
                "DkZSRUREWSBPU1dBTERPMBUGCisGAQQBgqg7AwMEBxMFUk9NQU4wGAYKKwYBBAGCqDsDBAQKEwhH\n" +
                "VUVSUkVSTzAmBgorBgEEAYKoOwMHBBgTFlBSRU5TQSBONDY2MiBZIExPR1JPTk8wGQYKKwYBBAGC\n" +
                "qDsDCAQLEwkwMjI0Mzk3MDAwFQYKKwYBBAGCqDsDCQQHEwVRdWl0bzAXBgorBgEEAYKoOwMMBAkT\n" +
                "B0VDVUFET1IwHQYKKwYBBAGCqDsDCwQPEw0xNzEzODkwNzc4MDAxMCAGCisGAQQBgqg7AzMEEhMQ\n" +
                "U09GVFdBUkUtQVJDSElWTzAgBgNVHREEGTAXgRVlbG1hbmdsYXIyOEBnbWFpbC5jb20wggHhBgNV\n" +
                "HR8EggHYMIIB1DCCAdCgggHMoIIByIaB1mxkYXA6Ly9iY2VxbGRhcHN1YnAxLmJjZS5lYy9jbj1D\n" +
                "UkwxMTI1LGNuPUFDJTIwQkFOQ08lMjBDRU5UUkFMJTIwREVMJTIwRUNVQURPUixsPVFVSVRPLG91\n" +
                "PUVOVElEQUQlMjBERSUyMENFUlRJRklDQUNJT04lMjBERSUyMElORk9STUFDSU9OLUVDSUJDRSxv\n" +
                "PUJBTkNPJTIwQ0VOVFJBTCUyMERFTCUyMEVDVUFET1IsYz1FQz9jZXJ0aWZpY2F0ZVJldm9jYXRp\n" +
                "b25MaXN0P2Jhc2WGNGh0dHA6Ly93d3cuZWNpLmJjZS5lYy9DUkwvZWNpX2JjZV9lY19jcmxmaWxl\n" +
                "Y29tYi5jcmykgbYwgbMxCzAJBgNVBAYTAkVDMSIwIAYDVQQKExlCQU5DTyBDRU5UUkFMIERFTCBF\n" +
                "Q1VBRE9SMTcwNQYDVQQLEy5FTlRJREFEIERFIENFUlRJRklDQUNJT04gREUgSU5GT1JNQUNJT04t\n" +
                "RUNJQkNFMQ4wDAYDVQQHEwVRVUlUTzElMCMGA1UEAxMcQUMgQkFOQ08gQ0VOVFJBTCBERUwgRUNV\n" +
                "QURPUjEQMA4GA1UEAxMHQ1JMMTEyNTArBgNVHRAEJDAigA8yMDIwMTEyMDE4MDAzNVqBDzIwMjIx\n" +
                "MTEyMTgxNzQ2WjAfBgNVHSMEGDAWgBRIot8jHx34LFF6jAPNSTKlCcGUqzAdBgNVHQ4EFgQUx1NW\n" +
                "3Mbb77UZFGJp8XFbRRHuh0wwCQYDVR0TBAIwADAZBgkqhkiG9n0HQQAEDDAKGwRWOC4xAwIEMDAN\n" +
                "BgkqhkiG9w0BAQsFAAOCAgEAMeXL+xV7MoaFLPWAcXtnh+uZy7tSrljuX87iWtYF9sJI9WRjLNO/\n" +
                "AkFgW5p/oIjgcDcXbInzS55BkdrU5kROWzuXnQcr7F00eqFsBkn4sOVyFA5tCGfEonZ9F13K82Lu\n" +
                "3inKUu/yqDIc7a4CGnr/PbolEPedUFfIe/qH9ZwNzQ6Ez2S6ngStlGROaFfPuEu9eKZqiMJnR6ED\n" +
                "P0OsajH8wSr5D37F2+TEXU6AKD7JMvAyP0q/o6ZWOhhjYm9fUyq0n4bogfLi2K2L6YdGAD4YIy0q\n" +
                "cxD6hjJ/TSsMtum3O5ZrsBSwEr8Q6UcGNnHdaONGbF0TNB/EA+mQxmLuXiLkrzKmcscQaelotpTG\n" +
                "L0Bn+2703PzzcEIlQF/VwUXtlonY3RzuluYpv5M0Xxn2rI7E0b+bmKkMK1Jk/HYScO6/Td5wFUrC\n" +
                "9cfps8oh3JOQq97c2IXS7CZS4cCPg3RKvd6nhvvoF3bwDlpT7qAle5Cty/13pT8eEfQyixS9E3gn\n" +
                "vXRmaUrOlmXd3anAKPinKV4ksmfUmo2WC8o/IXYRg609CrVXf0OCAr7zHSNNKpfGYOInrY4t7dtQ\n" +
                "zleJBb7+qFHrF2UnSFi3vxCKokznFEjC4hf7cIID0a/5jRiIuOv5H7DRvrprz7+gvxdO+OV3vwZP\n" +
                "rsC180BGAu3r30+gKmTXqNU=\n" +
                "</ds:X509Certificate>\n" +
                "</ds:X509Data>\n" +
                "<ds:KeyValue>\n" +
                "<ds:RSAKeyValue>\n" +
                "<ds:Modulus>\n" +
                "zLTrvSyEXA5ClcekFdCCLu2FCBO6caHgBDWAGOCnP9zs0G15CTgvWjaKIXhzJ9u0RcYrHEJzC/3d\n" +
                "20htMimftORB3bioreP1mdFL1ashWUCf0VlUWTUjFHiXY4xJMZnlT8oetp5M29EX8cP/M+UOlKt2\n" +
                "v2r8b4Eq8S1k4bzCQNgU9z2V/9FyfZ3JF7c4PoYyKhNkADxDkT/8UzgG38BnqwQk8CYrrGE4fk9U\n" +
                "gc3wWAImCVvmEbDHjRk4AaSEpzMyJE9ex+K1soKCmI0eFfHTRG8F6A2MDSIAHh1Cm0sYIx9MZsLF\n" +
                "2mIqwwwpRmGkxw+GO9n0Fe5TjzupjOPuqjhNRQ==\n" +
                "</ds:Modulus>\n" +
                "<ds:Exponent>AQAB</ds:Exponent>\n" +
                "</ds:RSAKeyValue>\n" +
                "</ds:KeyValue>\n" +
                "</ds:KeyInfo>\n" +
                "<ds:Object Id=\"Signature925142-Object725621\"><etsi:QualifyingProperties Target=\"#Signature925142\"><etsi:SignedProperties Id=\"Signature925142-SignedProperties420748\"><etsi:SignedSignatureProperties><etsi:SigningTime>2021-02-06T18:44:52-05:00</etsi:SigningTime><etsi:SigningCertificate><etsi:Cert><etsi:CertDigest><ds:DigestMethod Algorithm=\"http://www.w3.org/2000/09/xmldsig#sha1\"></ds:DigestMethod><ds:DigestValue>6j0y9JYqJbaIdxH38AuEitPlGyc=</ds:DigestValue></etsi:CertDigest><etsi:IssuerSerial><ds:X509IssuerName>CN=AC BANCO CENTRAL DEL ECUADOR,L=QUITO,OU=ENTIDAD DE CERTIFICACION DE INFORMACION-ECIBCE,O=BANCO CENTRAL DEL ECUADOR,C=EC</ds:X509IssuerName><ds:X509SerialNumber>1533439907</ds:X509SerialNumber></etsi:IssuerSerial></etsi:Cert></etsi:SigningCertificate></etsi:SignedSignatureProperties><etsi:SignedDataObjectProperties><etsi:DataObjectFormat ObjectReference=\"#Reference-ID-23296\"><etsi:Description>contenido comprobante</etsi:Description><etsi:MimeType>text/xml</etsi:MimeType></etsi:DataObjectFormat></etsi:SignedDataObjectProperties></etsi:SignedProperties></etsi:QualifyingProperties></ds:Object></ds:Signature></factura>]]>");

            authorizations.getAutorizacion().add(authorizationTest);

            if (authorizations != null && authorizations.getAutorizacion().size() > 0) {
                autorizacion = authorizations.getAutorizacion().get(0);
                String errorMessage = "";
                if (autorizacion != null && autorizacion.getMensajes() != null) {
                    for (ec.com.newsolutions.web.wsdl.sri.authorization.Mensaje mensaje : autorizacion.getMensajes().getMensaje()) {
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
                if (SRIDocumentStateEnum.AUTHORIZED.state().equals(autorizacion.getEstado())) {
                    electronicDocumentService.updateSriAuthorizedFields(tributaryDocument.getElectronicDocument(),
                        SRIDocumentStateEnum.AUTHORIZED, Utils.xmlGregorianCalendarToInstant(autorizacion.getFechaAutorizacion()));
                } else {
                    throw new ElectronicDocumentException(ProccessElectronicDocument.AUTHORIZATION, tributaryDocument.getElectronicDocument().getAccessKey(), errorMessage);

                }
            }
            return autorizacion;

        } catch (Exception e) {
            throw new ElectronicDocumentException(ProccessElectronicDocument.AUTHORIZATION, tributaryDocument.getElectronicDocument().getAccessKey(), e.getMessage());
        }
    }

    private void generateAuthorizedDocumentPdf(TributaryDocument tributaryDocument) {
        try {
            reportInvoiceClientService.execute(tributaryDocument);
        } catch (Exception e) {

        }
    }


}
