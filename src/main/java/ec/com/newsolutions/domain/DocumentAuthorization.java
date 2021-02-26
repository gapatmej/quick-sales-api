package ec.com.newsolutions.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "document_authorization", uniqueConstraints=@UniqueConstraint(columnNames={"document_id", "emission_point_id"}))
public class DocumentAuthorization extends AbstractMainEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="document_id", nullable = false)
    private Document document;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="emission_point_id", nullable = false)
    private EmissionPoint emissionPoint;

    @Column(name = "since")
    private int since;

    @Column(name = "until")
    private int until;

    @Column(name = "sequence")
    private int sequence;

    public Document getDocument() {
        return document;
    }

    public void setDocument(Document document) {
        this.document = document;
    }

    public EmissionPoint getEmissionPoint() {
        return emissionPoint;
    }

    public void setEmissionPoint(EmissionPoint emissionPoint) {
        this.emissionPoint = emissionPoint;
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
}
