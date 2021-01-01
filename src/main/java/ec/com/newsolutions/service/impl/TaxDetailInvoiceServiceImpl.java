package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.TaxDetailInvoice;
import ec.com.newsolutions.repository.TaxDetailInvoiceRepository;
import ec.com.newsolutions.service.TaxDetailInvoiceService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;


@Service
@Transactional
public class TaxDetailInvoiceServiceImpl extends AbstractService implements TaxDetailInvoiceService {

    private final TaxDetailInvoiceRepository taxDetailInvoiceRepository;

    public TaxDetailInvoiceServiceImpl(TaxDetailInvoiceRepository taxDetailInvoiceRepository) {
        super(TaxDetailInvoiceServiceImpl.class);
        this.taxDetailInvoiceRepository = taxDetailInvoiceRepository;
    }

    @Override
    public void save(TaxDetailInvoice taxDetailInvoice) {
        taxDetailInvoiceRepository.save(taxDetailInvoice);
    }

    @Override
    public void saveAll(Set<TaxDetailInvoice> taxesDetailInvoice) {
        taxesDetailInvoice.forEach(tDI->{
            save(tDI);
        });
    }

    @Override
    public void deleteByInvoiceClientId(Long invoiceClientId) {
        taxDetailInvoiceRepository.deleteByInvoiceClientId(invoiceClientId);
    }

    @Override
    public void deleteByDetailInvoiceClientIdIn(List<Long> detailInvoiceClientIds) {
        taxDetailInvoiceRepository.deleteByDetailInvoiceClientIdIn(detailInvoiceClientIds);
    }

    @Override
    public Optional<TaxDetailInvoice> findOne(Long id) {
        return Optional.empty();
    }
}
