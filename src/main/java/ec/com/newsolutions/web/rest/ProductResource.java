package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.domain.Product;
import ec.com.newsolutions.service.ProductService;
import ec.com.newsolutions.service.dto.ProductDTO;
import ec.com.newsolutions.utils.GsonUtils;
import ec.com.newsolutions.web.rest.errors.BadRequestAlertException;

import ec.com.newsolutions.web.rest.errors.IdExistException;
import ec.com.newsolutions.web.rest.errors.InvalidIdException;
import ec.com.newsolutions.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductResource extends AbstractResource {

    private final ProductService productService;

    public ProductResource(ProductService productService) {

        super(ProductResource.class, "product");
        this.productService = productService;
    }

    @PostMapping("/products")
    public ResponseEntity<ProductDTO> createProduct(@Valid @RequestBody ProductDTO productDTO) throws URISyntaxException {
        log.debug("REST request to save Product : {}", GsonUtils.entityToJson(productDTO));
        if (productDTO.getId() != null) throw new IdExistException(entityName);

        ProductDTO result = productService.save(productDTO);
        return ResponseEntity.created(new URI("/api/products/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(true, entityName, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/products")
    public ResponseEntity<ProductDTO> updateProduct(@Valid @RequestBody ProductDTO productDTO) throws URISyntaxException {
        log.debug("REST request to update Product : {}", GsonUtils.entityToJson(productDTO));
        if (productDTO.getId() == null) throw new InvalidIdException(entityName);

        ProductDTO result = productService.save(productDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(true, entityName, productDTO.getId().toString()))
            .body(result);
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductDTO>> getAllProducts(@RequestParam(name = "query", required = false) String query, Pageable pageable) {
        log.debug("REST request to get a page of Products");
        Page<ProductDTO> page = productService.findAll(pageable, query);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable Long id) {
        log.debug("REST request to get Product : {}", id);
        Optional<ProductDTO> productDTO = productService.findOne(id);
        return ResponseUtil.wrapOrNotFound(productDTO);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.debug("REST request to delete Product : {}", id);
        productService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(true, entityName, id.toString())).build();
    }
}
