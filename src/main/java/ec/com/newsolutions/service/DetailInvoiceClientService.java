package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.DetailInvoiceClient;
import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.service.dto.DetailInvoiceClientDTO;
import java.util.List;
import java.util.Set;

public interface DetailInvoiceClientService extends AbstractService<DetailInvoiceClient> {

    void save(DetailInvoiceClient detailInvoiceClient);
    void saveAll(Set<DetailInvoiceClient> detailsInvoiceClient);
    void deleteByInvoiceClient(Long idInvoiceClient);
    void deleteByIdIn(List<Long> ids);
    void build(InvoiceClient invoiceClient);
}
