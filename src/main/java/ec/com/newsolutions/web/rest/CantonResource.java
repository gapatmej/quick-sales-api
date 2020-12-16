package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.service.CantonService;
import ec.com.newsolutions.service.dto.CantonDTO;
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
public class CantonResource extends AbstractResource{

    private final CantonService cantonService;

    public CantonResource(CantonService cantonService) {
        super(CantonResource.class, "canton");
        this.cantonService = cantonService;
    }

    @GetMapping("/cantons")
    public ResponseEntity<List<CantonDTO>> getAllCantons(String search, Pageable pageable) {
        log.debug("REST request to get a page of Cantons");
        Page<CantonDTO> page = cantonService.findAll(search, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
