package ec.com.newsolutions.service.dto;

public class DocumentAuthorizationDTO extends AbstractMainDTO {

    private Long documentId;

    private Long emissionPointId;

    private int since;

    private int until;

    private int sequence;

    private boolean deleted;

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public Long getEmissionPointId() {
        return emissionPointId;
    }

    public void setEmissionPointId(Long emissionPointId) {
        this.emissionPointId = emissionPointId;
    }

    public int getSince() {
        return since;
    }

    public void setSince(int since) {
        this.since = since;
    }

    public int getUntil() {
        return until;
    }

    public void setUntil(int until) {
        this.until = until;
    }

    public int getSequence() {
        return sequence;
    }

    public void setSequence(int sequence) {
        this.sequence = sequence;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }
}
