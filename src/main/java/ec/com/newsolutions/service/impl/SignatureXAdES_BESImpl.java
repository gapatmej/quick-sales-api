package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.service.SignatureXAdES_BES;
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
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class SignatureXAdES_BESImpl implements SignatureXAdES_BES {

    // Almac�n PKCS12 con el que se desea realizar la firma
    protected String PKCS12_RESOURCE = "D:\\electronicDocuments\\william_javier_zambrano_valencia.p12";
    // Constrase�a de acceso a la clave privada del usuario
    protected String PKCS12_PASSWORD = "K0rea4k1978";
    // Path del archivo a firmar
    protected String pathArchivoEmitido = "D:\\electronicDocuments\\Invoice\\issued\\pruebas.xml";
    // Path del archivo firmado
    protected String pathArchivoFirmado = "D:\\electronicDocuments\\Invoice\\signed\\pruebas.xml";

    public DataToSign createDataToSign() {

        DataToSign dataToSign = new DataToSign();
        dataToSign.setXadesFormat(EnumFormatoFirma.XAdES_BES);
        dataToSign.setEsquema(XAdESSchemas.XAdES_132);
        dataToSign.setXMLEncoding("UTF-8");
        dataToSign.setEnveloped(true);

        dataToSign.addObject(new ObjectToSign(new InternObjectToSign("comprobante"), "contenido comprobante", null, "text/xml", null));

        Document docToSign = getDocument(pathArchivoEmitido);
        dataToSign.setDocument(docToSign);

        return dataToSign;
    }

    /**
     * <p>
     * Ejecución del ejemplo. La ejecución consistir� en la firma de los datos creados por el método abstracto <code>createDataToSign</code> mediante
     * el certificado declarado en la constante <code>PKCS12_FILE</code>. El resultado del proceso de firma ser� almacenado en un fichero XML en el
     * directorio correspondiente a la constante <code>OUTPUT_DIRECTORY</code> del usuario bajo el nombre devuelto por el método abstracto
     * <code>getSignFileName</code>
     * </p>
     */

    @Override
    public void execute() {

        // Obtencion del gestor de claves
        IPKStoreManager storeManager = getPKStoreManager();
        if (storeManager == null) {
            System.err.println("El gestor de claves no se ha obtenido correctamente.");
            return;
        }

        // Obtencion del certificado para firmar. Utilizaremos el primer
        // certificado del almacen.
        X509Certificate certificate = getFirstCertificate(storeManager);
        if (certificate == null) {
            System.err.println("No existe ning�n certificado para firmar.");
            return;
        }

        // Obtención de la clave privada asociada al certificado
        PrivateKey privateKey;
        try {
            privateKey = storeManager.getPrivateKey(certificate);
        } catch (CertStoreException e) {
            System.err.println("Error al acceder al almacén.");
            return;
        }

        // Obtención del provider encargado de las labores criptogr�ficas
        Provider provider = storeManager.getProvider(certificate);

        /*
         * Creación del objeto que contiene tanto los datos a firmar como la configuración del tipo de firma
         */
        DataToSign dataToSign = createDataToSign();

        /*
         * Creación del objeto encargado de realizar la firma
         */
        FirmaXML firma = new FirmaXML();

        // Firmamos el documento
        Document docSigned = null;
        try {
            Object[] res = firma.signFile(certificate, dataToSign, privateKey, provider);
            docSigned = (Document) res[0];
        } catch (Exception ex) {
            System.err.println("Error realizando la firma");
            ex.printStackTrace();
            return;
        }

        // Guardamos la firma a un fichero en el home del usuario
        saveDocumentToFile(docSigned, pathArchivoFirmado);
    }

    /**
     * <p>
     * Escribe el documento a un fichero.
     * </p>
     *
     * @param document
     *            El documento a imprmir
     * @param pathfile
     *            El path del fichero donde se quiere escribir.
     */
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

    /**
     * <p>
     * Escribe el documento a un fichero. Esta implementacion es insegura ya que dependiendo del gestor de transformadas el contenido podr�a ser
     * alterado, con lo que el XML escrito no ser�a correcto desde el punto de vista de validez de la firma.
     * </p>
     *
     * @param document
     *            El documento a imprmir
     * @param pathfile
     *            El path del fichero donde se quiere escribir.
     */
    @SuppressWarnings("unused")
    private void saveDocumentToFileUnsafeMode(Document document, String pathfile) {
        TransformerFactory tfactory = TransformerFactory.newInstance();
        Transformer serializer;
        try {
            serializer = tfactory.newTransformer();

            serializer.transform(new DOMSource(document), new StreamResult(new File(pathfile)));
        } catch (TransformerException e) {
            System.err.println("Error al salvar el documento");
            e.printStackTrace();
            System.exit(-1);
        }
    }

    /**
     * <p>
     * Devuelve el <code>Document</code> correspondiente al <code>resource</code> pasado como parámetro
     * </p>
     *
     * @param resource
     *            El recurso que se desea obtener
     * @return El <code>Document</code> asociado al <code>resource</code>
     */
    protected Document getDocument(String resource) {
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

    /**
     * <p>
     * Devuelve el contenido del documento XML correspondiente al <code>resource</code> pasado como parámetro
     * </p>
     * como un <code>String</code>
     *
     * @param resource
     *            El recurso que se desea obtener
     * @return El contenido del documento XML como un <code>String</code>
     */
    protected String getDocumentAsString(String resource) {
        Document doc = getDocument(resource);
        TransformerFactory tfactory = TransformerFactory.newInstance();
        Transformer serializer;
        StringWriter stringWriter = new StringWriter();
        try {
            serializer = tfactory.newTransformer();
            serializer.transform(new DOMSource(doc), new StreamResult(stringWriter));
        } catch (TransformerException e) {
            System.err.println("Error al imprimir el documento");
            e.printStackTrace();
            System.exit(-1);
        }

        return stringWriter.toString();
    }

    /**
     * <p>
     * Devuelve el gestor de claves que se va a utilizar
     * </p>
     *
     * @return El gestor de claves que se va a utilizar</p>
     */
    private IPKStoreManager getPKStoreManager() {
        IPKStoreManager storeManager = null;
        try {
            KeyStore ks = KeyStore.getInstance("PKCS12");
            ks.load(new FileInputStream(PKCS12_RESOURCE), PKCS12_PASSWORD.toCharArray());
            storeManager = new KSStore(ks, new PassStoreKS(PKCS12_PASSWORD));
        } catch (KeyStoreException ex) {
            System.err.println("No se puede generar KeyStore PKCS12");
            ex.printStackTrace();
            //System.exit(-1);
        } catch (NoSuchAlgorithmException ex) {
            System.err.println("No se puede generar KeyStore PKCS12");
            ex.printStackTrace();
            //System.exit(-1);
        } catch (CertificateException ex) {
            System.err.println("No se puede generar KeyStore PKCS12");
            ex.printStackTrace();
            //System.exit(-1);
        } catch (IOException ex) {
            System.err.println("No se puede generar KeyStore PKCS12");
            ex.printStackTrace();
            //System.exit(-1);
        }
        return storeManager;
    }

    /**
     * <p>
     * Recupera el primero de los certificados del almacén.
     * </p>
     *
     * @param storeManager
     *            Interfaz de acceso al almacén
     * @return Primer certificado disponible en el almacén
     */
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
             //   x509Certificate.checkValidity(new Date());
                certificate = x509Certificate;
                return certificate;
            } catch (Exception e) {
                System.err.println("No se encontro una firma de tipo digitalSignature con fecha valida ");
            }
        }

        return certificate;
    }



}
