package ec.com.newsolutions.service.errors;

import ec.com.newsolutions.service.errors.enumeration.ProccessElectronicDocument;

public class ElectronicDocumentException extends RuntimeException {

    public ElectronicDocumentException(ProccessElectronicDocument proccessElectronicDocument, String accessKey, String message) {
        super(String.format("Access Key = %s ; Proccess= %s ; Error = %s ",accessKey,proccessElectronicDocument.description(),message));
    }
}
