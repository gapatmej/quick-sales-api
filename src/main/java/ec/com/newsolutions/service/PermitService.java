package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.Permit;
import ec.com.newsolutions.service.dto.PermitDTO;

import java.util.List;

public interface PermitService extends AbstractServiceRest<PermitDTO>, AbstractService<Permit> {

    List<PermitDTO> findAllByAuthoritiesIn(List<Long> authorityIds);

}
