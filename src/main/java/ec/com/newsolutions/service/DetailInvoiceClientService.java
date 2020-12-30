package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.DetailInvoiceClient;
import ec.com.newsolutions.service.dto.DetailInvoiceClientDTO;
import java.util.List;

public interface DetailInvoiceClientService extends AbstractService<DetailInvoiceClientDTO, DetailInvoiceClient> {

    List<DetailInvoiceClientDTO> saveAll(List<DetailInvoiceClientDTO> detailInvoiceClientDTOS);
    void deleteByInvoiceClient(Long idInvoiceClient);}
