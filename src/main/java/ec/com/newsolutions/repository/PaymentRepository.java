package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.Payment;

import org.springframework.stereotype.Repository;

@Repository
public interface PaymentRepository extends JpaRepositoryCustom<Payment, Long> {

    void deleteByInvoiceClientId(Long invoiceClientId);
}
