package ec.com.newsolutions.service;


import ec.com.newsolutions.service.dto.TaxDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface TaxService {

    TaxDTO save(TaxDTO taxDTO);

    Page<TaxDTO> findAll(Pageable pageable);

    Optional<TaxDTO> findOne(Long id);

    void delete(Long id);
}
