package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.TaxDetailInvoice;

import java.util.List;
import java.util.Set;

public interface TaxDetailInvoiceService extends AbstractService<TaxDetailInvoice> {

    void saveAll(Set<TaxDetailInvoice> taxesDetailInvoice);
    void deleteByInvoiceClientId(Long invoiceClientId);
    void deleteByDetailInvoiceClientIdIn(List<Long> detailInvoiceClientIds);

}
