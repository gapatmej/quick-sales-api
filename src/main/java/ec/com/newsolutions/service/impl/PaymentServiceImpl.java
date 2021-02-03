package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.Payment;
import ec.com.newsolutions.service.PaymentService;
import ec.com.newsolutions.repository.PaymentRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class PaymentServiceImpl extends AbstractService implements PaymentService {

    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        super(PaymentServiceImpl.class);
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void saveAll(Set<Payment> payments) {
        payments.forEach(p->{
                save(p);
            }
        );
    }

    @Override
    public void deleteByInvoiceClientId(Long invoiceClientId) {
        paymentRepository.deleteByInvoiceClientId(invoiceClientId);
    }

    @Override
    public Payment save(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public Optional<Payment> findOne(Long id) {
        throw new UnsupportedOperationException();
    }
}
