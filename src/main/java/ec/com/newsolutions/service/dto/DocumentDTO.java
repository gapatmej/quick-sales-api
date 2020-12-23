package ec.com.newsolutions.service.dto;

import java.util.ArrayList;
import java.util.List;

public class DocumentDTO extends AbstractMainDTO{

    private Long organizationId;

    private String code;

    private String name;

    private String prefix;

    private int since;

    private int until;

    private int sequence;

    private String description;

    private List<DocumentAuthorizationDTO> documentAuthorizations = new ArrayList<>();

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
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
