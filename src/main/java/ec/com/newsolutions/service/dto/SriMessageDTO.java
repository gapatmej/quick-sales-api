package ec.com.newsolutions.service.dto;

public class SriMessageDTO extends AbstractMainDTO {

    private Integer identificator;

    private String message;

    private String additionalInformation;

    private String type;

    private Long electronicDocumentId;

    public Integer getIdentificator() {
        return identificator;
    }

    public void setIdentificator(Integer identificator) {
        this.identificator = identificator;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAdditionalInformation() {
        return additionalInformation;
    }

    public void setAdditionalInformation(String additionalInformation) {
        this.additionalInformation = additionalInformation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Long getElectronicDocumentId() {
        return electronicDocumentId;
    }

    public void setElectronicDocumentId(Long electronicDocumentId) {
        this.electronicDocumentId = electronicDocumentId;
    }
}
