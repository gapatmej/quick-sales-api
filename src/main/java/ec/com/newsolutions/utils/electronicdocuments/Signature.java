package ec.com.newsolutions.utils.electronicdocuments;

import ec.com.newsolutions.domain.ElectronicDocument;
import ec.com.newsolutions.domain.Organization;
import ec.com.newsolutions.domain.enumeration.ReceiptTypeEnum;

public class Signature {
    private String signedPath;
    private String certificatePath;
    private String xmlPath;
    private String passwordCertificate;

    public Signature(String signedPath, String certificatePath, String xmlPath, String passwordCertificate) {
        this.signedPath = signedPath;
        this.certificatePath = certificatePath;
        this.xmlPath = xmlPath;
        this.passwordCertificate = passwordCertificate;
    }

    public Signature(ElectronicDocument electronicDocument) {
        this.certificatePath = ElectronicDocumentsUtils.getCertificatePath(electronicDocument);
        this.signedPath = ElectronicDocumentsUtils.getSignedPathWithAccessKey(electronicDocument);
        this.xmlPath = ElectronicDocumentsUtils.getXMlPathWithAccessKey(electronicDocument);
        this.passwordCertificate = electronicDocument.getOrganization().getCertificatePassword();
    }

    public String getSignedPath() {
        return signedPath;
    }

    public void setSignedPath(String signedPath) {
        this.signedPath = signedPath;
    }

    public String getCertificatePath() {
        return certificatePath;
    }

    public void setCertificatePath(String certificatePath) {
        this.certificatePath = certificatePath;
    }

    public String getXmlPath() {
        return xmlPath;
    }

    public void setXmlPath(String xmlPath) {
        this.xmlPath = xmlPath;
    }

    public String getPasswordCertificate() {
        return passwordCertificate;
    }

    public void setPasswordCertificate(String passwordCertificate) {
        this.passwordCertificate = passwordCertificate;
    }
}
