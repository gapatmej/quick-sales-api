package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.TaxInvoice;

import java.util.List;
import java.util.Optional;
import java.util.Set;


public interface TaxInvoiceService extends AbstractService<TaxInvoice> {

    void saveAll(Set<TaxInvoice> taxesInvoice);
    void deleteByInvoiceClientId(Long invoiceClientId);

}
