package ec.com.newsolutions.utils.electronicdocuments;

import ec.com.newsolutions.config.ApplicationProperties;
import ec.com.newsolutions.domain.ElectronicDocument;
import ec.com.newsolutions.domain.TributaryDocument;
import ec.com.newsolutions.domain.enumeration.ReceiptTypeEnum;
import ec.com.newsolutions.utils.Utils;
import org.springframework.stereotype.Component;

@Component
public class ElectronicDocumentsUtils {

    private static ApplicationProperties applicationProperties;

    public static void setApplicationProperties(ApplicationProperties applicationProperties) {
        ElectronicDocumentsUtils.applicationProperties = applicationProperties;
    }

    public static String getCertificatePath(TributaryDocument tributaryDocument) {
        return new StringBuilder(applicationProperties.getElectronicDocuments().getPaths().getMain())
            .append(Utils.DIRECTORY_SEPARATOR)
            .append(tributaryDocument.getOrganization().getId())
            .append(applicationProperties.getElectronicDocuments().getPaths().getCertificate())
            .append(Utils.DIRECTORY_SEPARATOR)
            .append(tributaryDocument.getOrganization().getCertificateName())
            .toString();
    }

    public static String getSignedPath(TributaryDocument tributaryDocument) {
        StringBuilder stringBuilder = ElectronicDocumentsUtils.getPathOrganization(tributaryDocument.getOrganization().getId(), tributaryDocument.getElectronicDocument().getReceiptType());
        stringBuilder.append(applicationProperties.getElectronicDocuments().getPaths().getSigned());
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
        StringBuilder stringBuilder = ElectronicDocumentsUtils.getPathOrganization(tributaryDocument.getOrganization().getId(), tributaryDocument.getElectronicDocument().getReceiptType())
            .append(applicationProperties.getElectronicDocuments().getPaths().getXml());
        return stringBuilder.toString();
    }

    public static String getXMlPathWithAccessKey(TributaryDocument tributaryDocument) {
        StringBuilder stringBuilder = new StringBuilder(getXMlPath(tributaryDocument))
            .append(Utils.DIRECTORY_SEPARATOR)
            .append(tributaryDocument.getElectronicDocument().getAccessKey())
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



}
