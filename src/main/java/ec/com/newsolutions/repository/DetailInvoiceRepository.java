package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.DetailInvoice;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the DetailInvoice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DetailInvoiceRepository extends JpaRepository<DetailInvoice, Long> {

}
