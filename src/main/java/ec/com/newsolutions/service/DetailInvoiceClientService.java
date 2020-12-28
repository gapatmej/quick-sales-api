package ec.com.newsolutions.service;

import ec.com.newsolutions.service.dto.DetailInvoiceClientDTO;
import java.util.List;

public interface DetailInvoiceClientService extends AbstractService<DetailInvoiceClientDTO> {

    List<DetailInvoiceClientDTO> saveAll(List<DetailInvoiceClientDTO> detailInvoiceClientDTOS);
    void deleteByInvoiceClient(Long idInvoiceClient);}
