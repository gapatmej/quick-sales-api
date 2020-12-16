package ec.com.newsolutions.service;

import ec.com.newsolutions.service.dto.ParishDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ParishService {

    Page<ParishDTO> findAll(String search, Pageable pageable);
}
