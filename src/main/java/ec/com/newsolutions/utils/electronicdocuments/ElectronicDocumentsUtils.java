package ec.com.newsolutions.utils.electronicdocuments;

import ec.com.newsolutions.config.ApplicationProperties;
import ec.com.newsolutions.domain.ElectronicDocument;
import ec.com.newsolutions.domain.enumeration.ReceiptTypeEnum;
import ec.com.newsolutions.utils.Utils;
import org.springframework.stereotype.Component;

@Component
public class ElectronicDocumentsUtils {

    private static ApplicationProperties applicationProperties;

    public static void setApplicationProperties(ApplicationProperties applicationProperties) {
        ElectronicDocumentsUtils.applicationProperties = applicationProperties;
    }

    public static String getCertificatePath(ElectronicDocument electronicDocument) {
        return new StringBuilder(applicationProperties.getElectronicDocuments().getPaths().getMain())
            .append(Utils.DIRECTORY_SEPARATOR)
            .append(electronicDocument.getOrganization().getId())
            .append(applicationProperties.getElectronicDocuments().getPaths().getCertificate())
            .append(Utils.DIRECTORY_SEPARATOR)
            .append(electronicDocument.getOrganization().getCertificateName())
            .toString();
    }

    public static String getSignedPath(ElectronicDocument electronicDocument) {
        StringBuilder stringBuilder = ElectronicDocumentsUtils.getPathOrganization(electronicDocument.getOrganization().getId(), electronicDocument.getReceiptType());
        stringBuilder.append(applicationProperties.getElectronicDocuments().getPaths().getSigned());
        return stringBuilder.toString();
    }

    public static String getSignedPathWithAccessKey(ElectronicDocument electronicDocument) {
        StringBuilder stringBuilder = new StringBuilder(getSignedPath(electronicDocument))
            .append(Utils.DIRECTORY_SEPARATOR)
            .append(electronicDocument.getAccessKey())
            .append(".xml");

        return stringBuilder.toString();
    }

    public static String getXMlPath(ElectronicDocument electronicDocument) {
        StringBuilder stringBuilder = ElectronicDocumentsUtils.getPathOrganization(electronicDocument.getOrganization().getId(), electronicDocument.getReceiptType())
            .append(applicationProperties.getElectronicDocuments().getPaths().getXml());
        return stringBuilder.toString();
    }

    public static String getXMlPathWithAccessKey(ElectronicDocument electronicDocument) {
        StringBuilder stringBuilder = new StringBuilder(getXMlPath(electronicDocument))
            .append(Utils.DIRECTORY_SEPARATOR)
            .append(electronicDocument.getAccessKey())
            .append(".xml");

        return stringBuilder.toString();

    }

    private static StringBuilder getPathOrganization(Long organizationId, ReceiptTypeEnum receiptTypeEnum) {
        StringBuilder stringBuilder = new StringBuilder(applicationProperties.getElectronicDocuments().getPaths().getMain())
            .append(Utils.DIRECTORY_SEPARATOR)
            .append(String.format("%d", organizationId));

        if (ReceiptTypeEnum.INVOICE.equals(receiptTypeEnum)) {
            stringBuilder.append(applicationProperties.getElectronicDocuments().getPaths().getDocuments().getInvoices());
        }

        return stringBuilder;
    }


}
