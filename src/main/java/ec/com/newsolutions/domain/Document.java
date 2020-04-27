package ec.com.newsolutions.domain;


import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;

import ec.com.newsolutions.domain.enumeration.DocumentTypeEnum;

/**
 * A Document.
 */
@Entity
@Table(name = "document")
public class Document implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "prefix")
    private String prefix;

    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private DocumentTypeEnum documentType;

    @Max(value = 99999999)
    @Column(name = "sequential")
    private Integer sequential;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Document name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public Document prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public DocumentTypeEnum getDocumentType() {
        return documentType;
    }

    public Document documentType(DocumentTypeEnum documentType) {
        this.documentType = documentType;
        return this;
    }

    public void setDocumentType(DocumentTypeEnum documentType) {
        this.documentType = documentType;
    }

    public Integer getSequential() {
        return sequential;
    }

    public Document sequential(Integer sequential) {
        this.sequential = sequential;
        return this;
    }

    public void setSequential(Integer sequential) {
        this.sequential = sequential;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Document)) {
            return false;
        }
        return id != null && id.equals(((Document) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Document{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", prefix='" + getPrefix() + "'" +
            ", documentType='" + getDocumentType() + "'" +
            ", sequential=" + getSequential() +
            "}";
    }
}
