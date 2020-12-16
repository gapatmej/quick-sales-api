package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.service.ParishService;
import ec.com.newsolutions.service.dto.ParishDTO;
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
public class ParishResource extends AbstractResource{

    private final ParishService parishService;

    public ParishResource(ParishService parishService) {
        super(ParishResource.class, "parish");
        this.parishService = parishService;
    }

    @GetMapping("/parishes")
    public ResponseEntity<List<ParishDTO>> getAllParishes(String search, Pageable pageable) {
        log.debug("REST request to get a page of Parishes");
        Page<ParishDTO> page = parishService.findAll(search, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
