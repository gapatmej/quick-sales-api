package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.service.SignatureXAdES;
import ec.com.newsolutions.service.impl.PassStoreKS;
import ec.com.newsolutions.utils.electronicdocuments.Signature;
import es.mityc.firmaJava.libreria.utilidades.UtilidadTratarNodo;
import es.mityc.firmaJava.libreria.xades.DataToSign;
import es.mityc.firmaJava.libreria.xades.EnumFormatoFirma;
import es.mityc.firmaJava.libreria.xades.FirmaXML;
import es.mityc.firmaJava.libreria.xades.XAdESSchemas;
import es.mityc.javasign.pkstore.CertStoreException;
import es.mityc.javasign.pkstore.IPKStoreManager;
import es.mityc.javasign.pkstore.keystore.KSStore;
import es.mityc.javasign.xml.refs.InternObjectToSign;
import es.mityc.javasign.xml.refs.ObjectToSign;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.Provider;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SignatureXAdESImpl implements SignatureXAdES {

    private Signature signature;

    @Override
    public void execute(Signature signature) {

        this.signature = signature;
        IPKStoreManager storeManager = getPKStoreManager();
        if (storeManager == null) {
            System.err.println("El gestor de claves no se ha obtenido correctamente.");
            return;
        }

        X509Certificate certificate = getFirstCertificate(storeManager);
        if (certificate == null) {
            System.err.println("No existe ning�n certificado para firmar.");
            return;
        }

        PrivateKey privateKey;
        try {
            privateKey = storeManager.getPrivateKey(certificate);
        } catch (CertStoreException e) {
            System.err.println("Error al acceder al almacén.");
            return;
        }

        Provider provider = storeManager.getProvider(certificate);
        DataToSign dataToSign = createDataToSign();
        FirmaXML firma = new FirmaXML();


        Document docSigned = null;
        try {
            Object[] res = firma.signFile(certificate, dataToSign, privateKey, provider);
            docSigned = (Document) res[0];
        } catch (Exception ex) {
            System.err.println("Error realizando la firma");
            ex.printStackTrace();
            return;
        }

        saveDocumentToFile(docSigned, signature.getSignedPath());
    }

    private DataToSign createDataToSign() {

        DataToSign dataToSign = new DataToSign();
        dataToSign.setXadesFormat(EnumFormatoFirma.XAdES_BES);
        dataToSign.setEsquema(XAdESSchemas.XAdES_132);
        dataToSign.setXMLEncoding("UTF-8");
        dataToSign.setEnveloped(true);

        dataToSign.addObject(new ObjectToSign(new InternObjectToSign("comprobante"), "contenido comprobante", null, "text/xml", null));

        Document docToSign = getDocument(signature.getXmlPath());
        dataToSign.setDocument(docToSign);

        return dataToSign;
    }

    private void saveDocumentToFile(Document document, String pathfile) {
        try {
            FileOutputStream fos = new FileOutputStream(pathfile);
            UtilidadTratarNodo.saveDocumentToOutputStream(document, fos, true);
        } catch (FileNotFoundException e) {
            System.err.println("Error al salvar el documento");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    private Document getDocument(String resource) {
        Document doc = null;
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        dbf.setNamespaceAware(true);
        try {
            doc = dbf.newDocumentBuilder().parse(new FileInputStream(resource));
        } catch (ParserConfigurationException ex) {
            System.err.println("Error al parsear el documento");
            ex.printStackTrace();
            //System.exit(-1);
        } catch (SAXException ex) {
            System.err.println("Error al parsear el documento");
            ex.printStackTrace();
            //System.exit(-1);
        } catch (IOException ex) {
            System.err.println("Error al parsear el documento");
            ex.printStackTrace();
            //System.exit(-1);
        } catch (IllegalArgumentException ex) {
            System.err.println("Error al parsear el documento");
            ex.printStackTrace();
            //System.exit(-1);
        }
        return doc;
    }

    private IPKStoreManager getPKStoreManager() {
        IPKStoreManager storeManager = null;
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(new FileInputStream(signature.getCertificatePath()), signature.getPasswordCertificate().toCharArray());
            storeManager = new KSStore(ks, new PassStoreKS(signature.getPasswordCertificate()));
        } catch (KeyStoreException ex) {
            System.err.println("No se puede generar KeyStore PKCS12");
            ex.printStackTrace();
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("No se puede generar KeyStore PKCS12");
            ex.printStackTrace();
        } catch (CertificateException ex) {
            System.err.println("No se puede generar KeyStore PKCS12");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.err.println("No se puede generar KeyStore PKCS12");
            ex.printStackTrace();
        }
        return storeManager;
    }

    private X509Certificate getFirstCertificate(final IPKStoreManager storeManager) {
        List<X509Certificate> certs = null;
        try {
            certs = storeManager.getSignCertificates().stream().filter(c -> c.getKeyUsage()[0]).collect(Collectors.toList());
        } catch (CertStoreException ex) {
            System.err.println("Fallo obteniendo listado de certificados");
            System.exit(-1);
        }
        if ((certs == null) || (certs.size() == 0)) {
            System.err.println("Lista de certificados vac�a");
            System.exit(-1);
        }

        X509Certificate certificate = null;

        for (X509Certificate x509Certificate : certs) {
            try {
                certificate = x509Certificate;
                return certificate;
            } catch (Exception e) {
                System.err.println("No se encontro una firma de tipo digitalSignature con fecha valida ");
            }
        }

        return certificate;
    }


}
