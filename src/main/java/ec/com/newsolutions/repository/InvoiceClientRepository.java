package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.InvoiceClient;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the InvoiceClient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoiceClientRepository extends JpaRepository<InvoiceClient, Long> {

    @Query("select ic from InvoiceClient ic join fetch ic.detailInvoices dt join fetch ic.company "+
        " join fetch ic.emissionPoint join fetch dt.product where ic.id = :id ")
    Optional<InvoiceClient> findById(Long id);
}
