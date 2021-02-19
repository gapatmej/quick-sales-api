package ec.com.newsolutions.utils.electronicdocuments;

import ec.com.newsolutions.config.ApplicationProperties;
import ec.com.newsolutions.domain.TributaryDocument;
import ec.com.newsolutions.domain.enumeration.ReceiptTypeEnum;
import ec.com.newsolutions.utils.Utils;
import ec.com.newsolutions.web.wsdl.sri.authorization.Autorizacion;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

@Component
public class ElectronicDocumentsUtils {

    private static ApplicationProperties applicationProperties;

    public static void setApplicationProperties(ApplicationProperties applicationProperties) {
        ElectronicDocumentsUtils.applicationProperties = applicationProperties;
    }

    public static String getCertificatePath(TributaryDocument tributaryDocument) {
        return new StringBuilder(applicationProperties.getPaths().getMain())
            .append(Utils.DIRECTORY_SEPARATOR)
            .append(tributaryDocument.getOrganization().getId())
            .append(applicationProperties.getPaths().getCertificate().getMain())
            .append(Utils.DIRECTORY_SEPARATOR)
            .append(tributaryDocument.getOrganization().getCertificateName())
            .toString();
    }

    public static String getAuthorizedPath(TributaryDocument tributaryDocument) {
        StringBuilder stringBuilder = ElectronicDocumentsUtils.getPathElectronicDocumentsWithDocumentType(tributaryDocument.getOrganization().getId(), tributaryDocument.getElectronicDocument().getReceiptType());
        stringBuilder.append(applicationProperties.getPaths().getElectronicDocuments().getAuthorized());
        return stringBuilder.toString();
    }

    public static String getAuthorizedPathWithAccessKey(TributaryDocument tributaryDocument) {
        StringBuilder stringBuilder = new StringBuilder(getAuthorizedPath(tributaryDocument))
            .append(Utils.DIRECTORY_SEPARATOR)
            .append(tributaryDocument.getElectronicDocument().getAccessKey())
            .append(".xml");

        return stringBuilder.toString();
    }

    public static String getAuthorizedPdfPath(TributaryDocument tributaryDocument) {
        StringBuilder stringBuilder = ElectronicDocumentsUtils.getPathElectronicDocumentsWithDocumentType(tributaryDocument.getOrganization().getId(), tributaryDocument.getElectronicDocument().getReceiptType());
        stringBuilder.append(applicationProperties.getPaths().getElectronicDocuments().getAuthorizedPdf());
        return stringBuilder.toString();
    }

    public static String getAuthorizedPdfPathWithAccessKey(TributaryDocument tributaryDocument) {
        StringBuilder stringBuilder = new StringBuilder(getAuthorizedPdfPath(tributaryDocument))
            .append(Utils.DIRECTORY_SEPARATOR)
            .append(tributaryDocument.getElectronicDocument().getAccessKey())
            .append(".pdf");

        return stringBuilder.toString();
    }

    public static String getSignedPath(TributaryDocument tributaryDocument) {
        StringBuilder stringBuilder = ElectronicDocumentsUtils.getPathElectronicDocumentsWithDocumentType(tributaryDocument.getOrganization().getId(), tributaryDocument.getElectronicDocument().getReceiptType());
        stringBuilder.append(applicationProperties.getPaths().getElectronicDocuments().getSigned());
        return stringBuilder.toString();
    }

    public static String getSignedPathWithAccessKey(TributaryDocument tributaryDocument) {
        StringBuilder stringBuilder = new StringBuilder(getSignedPath(tributaryDocument))
            .append(Utils.DIRECTORY_SEPARATOR)
            .append(tributaryDocument.getElectronicDocument().getAccessKey())
            .append(".xml");

        return stringBuilder.toString();
    }

    public static String getXMlPath(TributaryDocument tributaryDocument) {
        StringBuilder stringBuilder = ElectronicDocumentsUtils.getPathElectronicDocumentsWithDocumentType(tributaryDocument.getOrganization().getId(), tributaryDocument.getElectronicDocument().getReceiptType())
            .append(applicationProperties.getPaths().getElectronicDocuments().getXml());
        return stringBuilder.toString();
    }

    public static String getXMlPathWithAccessKey(TributaryDocument tributaryDocument) {
        StringBuilder stringBuilder = new StringBuilder(getXMlPath(tributaryDocument))
            .append(Utils.DIRECTORY_SEPARATOR)
            .append(tributaryDocument.getElectronicDocument().getAccessKey())
            .append(".xml");

        return stringBuilder.toString();

    }

    private static StringBuilder getPathOrganization(Long organizationId) {
        StringBuilder stringBuilder = new StringBuilder(applicationProperties.getPaths().getMain())
            .append(Utils.DIRECTORY_SEPARATOR)
            .append(String.format("%d", organizationId));

        return stringBuilder;
    }

    private static StringBuilder getPathElectronicDocumentsWithDocumentType(Long organizationId, ReceiptTypeEnum receiptTypeEnum) {
        StringBuilder stringBuilder = new StringBuilder(getPathOrganization(organizationId));
        stringBuilder.append(applicationProperties.getPaths().getElectronicDocuments().getMain());
        if(ReceiptTypeEnum.INVOICE.equals(receiptTypeEnum)){
            stringBuilder.append(applicationProperties.getPaths().getElectronicDocuments().getDocuments().getInvoices());
        }

        return stringBuilder;
    }

    public static int generateVerificationDigit(String accessKey) {
        int baseMultiplier = 7;
        int[] aux = new int[accessKey.length()];
        int multiplier = 2;
        int total = 0;
        int checker = 0;
        for (int i = aux.length - 1; i >= 0; --i) {
            aux[i] = Integer.parseInt("" + accessKey.charAt(i));
            aux[i] *= multiplier;
            ++multiplier;
            if (multiplier > baseMultiplier) {
                multiplier = 2;
            }
            total += aux[i];
        }

        if ((total == 0) || (total == 1))
            checker = 0;
        else {
            checker = (11 - (total % 11) == 11) ? 0 : 11 - (total % 11);
        }

        if (checker == 10) {
            checker = 1;
        }

        return checker;
    }

    public static void generateAuthorizedDocumentRide(TributaryDocument tributaryDocument, Autorizacion authorization) {
        final DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder docBuilder;
        try {

            docBuilder = docFactory.newDocumentBuilder();
            Document doc = docBuilder.newDocument();

            Element elementAutorizacion = doc.createElement("autorizacion");
            doc.appendChild(elementAutorizacion);

            Element elementEstado = doc.createElement("estado");
            elementEstado.appendChild(doc.createTextNode(authorization.getEstado()));
            elementAutorizacion.appendChild(elementEstado);

            Element numeroAutorizacion = doc.createElement("numeroAutorizacion");
            numeroAutorizacion.appendChild(doc.createTextNode(authorization.getNumeroAutorizacion()));
            elementAutorizacion.appendChild(numeroAutorizacion);

            Element fechaAutorizacion = doc.createElement("fechaAutorizacion");
            fechaAutorizacion.appendChild(doc.createTextNode(authorization.getFechaAutorizacion().toString()));
            elementAutorizacion.appendChild(fechaAutorizacion);

            Element ambiente = doc.createElement("ambiente");
            ambiente.appendChild(doc.createTextNode(authorization.getAmbiente()));
            elementAutorizacion.appendChild(ambiente);

            Element comprobante = doc.createElement("comprobante");
            comprobante.appendChild(doc.createCDATASection(authorization.getComprobante()));
            elementAutorizacion.appendChild(comprobante);

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource domSource = new DOMSource(doc);
            StreamResult streamResult = new StreamResult(new File(ElectronicDocumentsUtils.getAuthorizedPathWithAccessKey(tributaryDocument)));

            transformer.transform(domSource, streamResult);

        } catch (Exception e) {

        }

    }



}
