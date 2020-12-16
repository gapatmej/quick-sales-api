package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.service.ProvinceService;
import ec.com.newsolutions.service.dto.ProvinceDTO;
import io.github.jhipster.web.util.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProvinceResource extends AbstractResource{

    private final ProvinceService provinceService;

    public ProvinceResource(ProvinceService provinceService) {
        super(ProductResource.class, "province");
        this.provinceService = provinceService;
    }

    @GetMapping("/provinces")
    public ResponseEntity<List<ProvinceDTO>> getAllProvinces(String search, Pageable pageable) {
        log.debug("REST request to get a page of Provinces");
        Page<ProvinceDTO> page = provinceService.findAll(search, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
