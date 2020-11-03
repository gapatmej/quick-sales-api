package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.service.PermitService;
import ec.com.newsolutions.service.dto.PermitDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public class PermitServiceImpl implements PermitService {

    @Override
    public Page<PermitDTO> findAll(String search, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<PermitDTO> findOne(Long id) {
        return Optional.empty();
    }

}
