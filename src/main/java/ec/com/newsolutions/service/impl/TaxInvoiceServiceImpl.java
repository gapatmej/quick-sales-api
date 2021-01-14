package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.TaxInvoice;
import ec.com.newsolutions.repository.TaxInvoiceRepository;
import ec.com.newsolutions.service.TaxInvoiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class TaxInvoiceServiceImpl extends AbstractService implements TaxInvoiceService {

    private final TaxInvoiceRepository taxInvoiceRepository;

    public TaxInvoiceServiceImpl(TaxInvoiceRepository taxInvoiceRepository) {
        super(TaxInvoiceServiceImpl.class);
        this.taxInvoiceRepository = taxInvoiceRepository;
    }

    @Override
    public void saveAll(Set<TaxInvoice> taxesInvoice) {
        taxesInvoice.forEach(tI->{
            save(tI);
            }
        );
    }

    @Override
    public void deleteByInvoiceClientId(Long invoiceClientId) {
        taxInvoiceRepository.deleteByInvoiceClientId(invoiceClientId);
    }

    @Override
    public TaxInvoice save(TaxInvoice taxInvoice) {
        return taxInvoiceRepository.save(taxInvoice);
    }

    @Override
    public Optional<TaxInvoice> findOne(Long id) {
        throw new UnsupportedOperationException();
    }
}
