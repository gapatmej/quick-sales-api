package ec.com.newsolutions.service;

import ec.com.newsolutions.service.dto.PermitDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface PermitService {

    Page<PermitDTO> findAll(String search, Pageable pageable);

    Optional<PermitDTO> findOne(Long id);

    List<PermitDTO> findAllByAuthoritiesIn(List<Long> authorityIds);

}
