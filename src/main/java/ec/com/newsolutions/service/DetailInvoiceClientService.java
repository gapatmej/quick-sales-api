package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.DetailInvoiceClient;
import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.service.dto.DetailInvoiceClientDTO;
import java.util.List;
import java.util.Set;

public interface DetailInvoiceClientService extends AbstractService<DetailInvoiceClientDTO, DetailInvoiceClient> {

    List<DetailInvoiceClientDTO> saveAll(List<DetailInvoiceClientDTO> detailInvoiceClientDTOS);
    void deleteByInvoiceClient(Long idInvoiceClient);
    void build(Set<DetailInvoiceClient> detailsInvoiceClient);
}
