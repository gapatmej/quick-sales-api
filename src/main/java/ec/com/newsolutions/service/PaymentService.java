package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.Payment;

import java.util.Set;

public interface PaymentService extends AbstractService<Payment> {

    void saveAll(Set<Payment> payments);
    void deleteByInvoiceClientId(Long invoiceClientId);
}
