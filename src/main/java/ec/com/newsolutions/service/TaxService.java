package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.Tax;
import ec.com.newsolutions.service.dto.TaxDTO;

import java.util.List;


public interface TaxService extends AbstractServiceRest<TaxDTO>, AbstractService<Tax> {
    List<Tax> findByIdIn(List<Long> ids);
}
