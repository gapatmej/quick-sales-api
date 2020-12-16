package ec.com.newsolutions.service;

import ec.com.newsolutions.service.dto.ProvinceDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProvinceService {
    Page<ProvinceDTO> findAll(String search, Pageable pageable);
}
