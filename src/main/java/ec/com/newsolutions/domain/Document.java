package ec.com.newsolutions.domain;

import ec.com.newsolutions.domain.enumeration.BaseDocumentEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "document")
public class Document extends AbstractMainEntity{

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="organization_id", nullable = false)
    private Organization organization;

    @Enumerated(EnumType.STRING)
    @Column(name = "base_document", length = 50, nullable = false)
    private BaseDocumentEnum baseDocument;

    @Column(name = "code", length = 20, unique = true, nullable = false)
    private String code;

    @Column(name = "name", length = 50, nullable = false)
    private String name;

    @Column(name = "prefix", length = 20, unique = true)
    private String prefix;

    @Column(name = "since")
    private Integer since;

    @Column(name = "until")
    private Integer until;

    @Column(name = "sequence")
    private Integer sequence;

    @Column(name = "description", length = 200)
    private String description;

    @OneToMany(mappedBy = "document", fetch = FetchType.LAZY)
    private Set<DocumentAuthorization> documentAuthorizations = new HashSet<>();

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BaseDocumentEnum getBaseDocument() {
        return baseDocument;
    }

    public void setBaseDocument(BaseDocumentEnum baseDocument) {
        this.baseDocument = baseDocument;
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

    public Set<DocumentAuthorization> getDocumentAuthorizations() {
        return documentAuthorizations;
    }

    public void setDocumentAuthorizations(Set<DocumentAuthorization> documentAuthorizations) {
        this.documentAuthorizations = documentAuthorizations;
    }
}
