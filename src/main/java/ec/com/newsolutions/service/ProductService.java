package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.Product;
import ec.com.newsolutions.service.dto.ProductDTO;

import java.util.List;

public interface ProductService extends AbstractService<ProductDTO, Product> {

    List<Product> findByIdIn(List<Long> ids);
}
