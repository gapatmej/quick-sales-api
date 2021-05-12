package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.service.CantonService;
import ec.com.newsolutions.service.EmissionPointService;
import ec.com.newsolutions.service.dto.CantonDTO;
import ec.com.newsolutions.service.dto.EmissionPointDTO;
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
public class EmissionPointResource extends AbstractResource{

    private final EmissionPointService emissionPointService;

    public EmissionPointResource(EmissionPointService emissionPointService) {
        super(EmissionPointResource.class, "emissionPoint");
        this.emissionPointService = emissionPointService;
    }

    @GetMapping("/emission-points")
    public ResponseEntity<List<EmissionPointDTO>> getAll(String search, Pageable pageable) {
        log.debug("REST request to get a page of Emission Points");
        Page<EmissionPointDTO> page = emissionPointService.findAll(search, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
