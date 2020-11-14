package ec.com.newsolutions.service.impl;

import com.google.common.base.Strings;
import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.service.ProductService;
import ec.com.newsolutions.domain.Product;
import ec.com.newsolutions.repository.ProductRepository;
import ec.com.newsolutions.service.dto.ProductDTO;
import ec.com.newsolutions.service.mapper.ProductMapper;
import ec.com.newsolutions.utils.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link Product}.
 */
@Service
@Transactional
public class ProductServiceImpl extends AbstractService implements ProductService {

    private final ProductMapper productMapper;
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductMapper productMapper, ProductRepository productRepository) {
        super(ProductServiceImpl.class);
        this.productMapper = productMapper;
        this.productRepository = productRepository;
    }

    @Override
    public ProductDTO save(ProductDTO productDTO) {
        log.debug("Request to save Product : {}", GsonUtils.entityToJson(productDTO));
        Product product = productRepository.save(productMapper.toEntity(productDTO));
        return productMapper.toDto(product);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProductDTO> findAll(String search, Pageable pageable) {
        log.debug("Request to get all Products");
        return productRepository.findAll( UtilsSpecification.<Product>getSpecification(search), pageable).map(productMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ProductDTO> findOne(Long id) {
        log.debug("Request to get Product : {}", id);
        Optional<Product> productOpt =  productRepository.findById(id);
        return productOpt.map(productMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Product : {}", id);
        productRepository.deleteById(id);
    }
}
