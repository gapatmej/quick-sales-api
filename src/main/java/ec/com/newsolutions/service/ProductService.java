package ec.com.newsolutions.service;

import ec.com.newsolutions.domain.Product;
import ec.com.newsolutions.service.dto.ProductDTO;

import java.util.List;

public interface ProductService extends AbstractServiceRest<ProductDTO>, AbstractService<Product> {

    List<Product> findByIdIn(List<Long> ids);
}
