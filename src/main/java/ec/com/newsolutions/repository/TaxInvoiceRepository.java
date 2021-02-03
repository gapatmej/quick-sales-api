package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.TaxInvoice;

import org.springframework.stereotype.Repository;


@Repository
public interface TaxInvoiceRepository extends JpaRepositoryCustom<TaxInvoice, Long> {

    void deleteByInvoiceClientId(Long invoiceClientId);

}
