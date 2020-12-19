package ec.com.newsolutions.service;

import ec.com.newsolutions.service.dto.BankDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface BankService {

    BankDTO save(BankDTO bankDTO);

    Page<BankDTO> findAll(String search, Pageable pageable);

    Optional<BankDTO> findOne(Long id);

    void delete(Long id);
}
