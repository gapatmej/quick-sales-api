package ec.com.newsolutions.domain;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "additional_information")
public class AdditionalInformation extends AbstractAuditingEntity  {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "additional_field", nullable = false)
    private String additionalField;

    @Column(name = "value", nullable = false)
    private String value;

    @ManyToMany()
    @JoinTable(
        name = "invoice_client_additional_information"
    )
    private Set<InvoiceClient> invoicesClient = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdditionalField() {
        return additionalField;
    }

    public void setAdditionalField(String additionalField) {
        this.additionalField = additionalField;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Set<InvoiceClient> getInvoicesClient() {
        return invoicesClient;
    }

    public void setInvoicesClient(Set<InvoiceClient> invoicesClient) {
        this.invoicesClient = invoicesClient;
    }
}
