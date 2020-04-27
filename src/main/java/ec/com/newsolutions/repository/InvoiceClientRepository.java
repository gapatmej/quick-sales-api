package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.InvoiceClient;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the InvoiceClient entity.
 */
@SuppressWarnings("unused")
@Repository
public interface InvoiceClientRepository extends JpaRepository<InvoiceClient, Long> {

}
