package ec.com.newsolutions.service.dto;

public class DocumentAuthorizationDTO extends AbstractMainDTO {

    private Long documentId;

    private EmissionPointDTO emissionPoint;

    private Integer since;

    private Integer until;

    private Integer sequence;

    private boolean deleted;

    private BranchOfficeDTO branchOffice;

    public Long getDocumentId() {
        return documentId;
    }

    public void setDocumentId(Long documentId) {
        this.documentId = documentId;
    }

    public EmissionPointDTO getEmissionPoint() {
        return emissionPoint;
    }

    public void setEmissionPoint(EmissionPointDTO emissionPoint) {
        this.emissionPoint = emissionPoint;
    }

    public Integer getSince() {
        return since;
    }

    public void setSince(Integer since) {
        this.since = since;
    }

    public Integer getUntil() {
        return until;
    }

    public void setUntil(Integer until) {
        this.until = until;
    }

    public Integer getSequence() {
        return sequence;
    }

    public void setSequence(Integer sequence) {
        this.sequence = sequence;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public BranchOfficeDTO getBranchOffice() {
        return branchOffice;
    }

    public void setBranchOffice(BranchOfficeDTO branchOffice) {
        this.branchOffice = branchOffice;
    }

}
