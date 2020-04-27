package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.TaxInvoice;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the TaxInvoice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface TaxInvoiceRepository extends JpaRepository<TaxInvoice, Long> {

}
