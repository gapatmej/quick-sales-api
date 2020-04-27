package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.TaxDetailInvoice;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TaxDetailInvoice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaxDetailInvoiceRepository extends JpaRepository<TaxDetailInvoice, Long> {

}
