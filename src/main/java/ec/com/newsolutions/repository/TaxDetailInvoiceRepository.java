package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.TaxDetailInvoice;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxDetailInvoiceRepository extends JpaRepositoryCustom<TaxDetailInvoice, Long> {

    @Modifying
    @Query("DELETE FROM TaxDetailInvoice tdi  "
        + "WHERE tdi.detailInvoiceClient in (SELECT dti FROM DetailInvoiceClient dti where dti.invoiceClient.id = :invoiceClientId) ")
    void deleteByInvoiceClientId(@Param("invoiceClientId") Long invoiceClientId);

    void deleteByDetailInvoiceClientIdIn(List<Long> detailInvoiceClientIds);
}
