package ec.com.newsolutions.service.dto;

import ec.com.newsolutions.domain.enumeration.BaseDocumentEnum;

import java.util.ArrayList;
import java.util.List;

public class DocumentDTO extends AbstractMainDTO{

    private Long organizationId;

    private BaseDocumentEnum baseDocument;

    private String code;

    private String name;

    private String prefix;

    private Integer since;

    private Integer until;

    private Integer sequence;

    private String description;

    private List<DocumentAuthorizationDTO> documentAuthorizations = new ArrayList<>();

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public BaseDocumentEnum getBaseDocument() {
        return baseDocument;
    }

    public void setBaseDocument(BaseDocumentEnum baseDocument) {
        this.baseDocument = baseDocument;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<DocumentAuthorizationDTO> getDocumentAuthorizations() {
        return documentAuthorizations;
    }

    public void setDocumentAuthorizations(List<DocumentAuthorizationDTO> documentAuthorizations) {
        this.documentAuthorizations = documentAuthorizations;
    }
}
