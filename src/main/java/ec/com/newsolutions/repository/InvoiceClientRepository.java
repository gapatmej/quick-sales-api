package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.domain.enumeration.SRIDocumentStateEnum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceClientRepository extends JpaRepositoryCustom<InvoiceClient, Long> {

    @Modifying
    @Query("update InvoiceClient iC set iC.sriDocumentState = :sriDocumentStateEnum where iC.id = :invoiceClientId")
    void updateSriDocumentState(@Param("sriDocumentStateEnum") SRIDocumentStateEnum sriDocumentStateEnum, @Param("invoiceClientId")  Long invoiceClientId);
}
