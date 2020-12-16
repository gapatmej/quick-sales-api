package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.Product;

import ec.com.newsolutions.service.dto.ProductDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {

    ProductDTO save(ProductDTO productDTO);

    Page<ProductDTO> findAll(String search, Pageable pageable);

    Optional<ProductDTO> findOne(Long id);

    void delete(Long id);
}
