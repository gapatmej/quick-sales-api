package ec.com.newsolutions.service;

import ec.com.newsolutions.service.dto.PermitDTO;

import java.util.List;

public interface PermitService extends AbstractService<PermitDTO> {

    List<PermitDTO> findAllByAuthoritiesIn(List<Long> authorityIds);

}
