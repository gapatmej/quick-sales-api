package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.service.CellarService;
import ec.com.newsolutions.service.dto.CellarDTO;
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
public class CellarResource {

    private final Logger log = LoggerFactory.getLogger(CellarResource.class);

    private static final String ENTITY_NAME = "cellar";

    private final CellarService cellarService;

    public CellarResource(CellarService cellarService) {
        this.cellarService = cellarService;
    }

    @PostMapping("/cellars")
    public ResponseEntity<CellarDTO> createCellar(@Valid @RequestBody CellarDTO cellarDTO) throws URISyntaxException {
        log.debug("REST request to save Cellar : {}", cellarDTO);
        if (cellarDTO.getId() != null) throw new IdExistException(ENTITY_NAME);

        CellarDTO result = cellarService.save(cellarDTO);
        return ResponseEntity.created(new URI("/api/cellars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/cellars")
    public ResponseEntity<CellarDTO> updateCellar(@Valid @RequestBody CellarDTO cellarDTO) throws URISyntaxException {
        log.debug("REST request to update Cellar : {}", cellarDTO);
        if (cellarDTO.getId() == null) throw new InvalidIdException(ENTITY_NAME);

        CellarDTO result = cellarService.save(cellarDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(true, ENTITY_NAME, cellarDTO.getId().toString()))
            .body(result);
    }

    @GetMapping("/cellars")
    public ResponseEntity<List<CellarDTO>> getAllCellars(String search, Pageable pageable) {
        log.debug("REST request to get a page of Cellars");
        Page<CellarDTO> page = cellarService.findAll(search, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/cellars/{id}")
    public ResponseEntity<CellarDTO> getCellar(@PathVariable Long id) {
        log.debug("REST request to get Cellar : {}", id);
        Optional<CellarDTO> cellarDTO = cellarService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cellarDTO);
    }

    @DeleteMapping("/cellars/{id}")
    public ResponseEntity<Void> deleteCellar(@PathVariable Long id) {
        log.debug("REST request to delete Cellar : {}", id);
        cellarService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(true, ENTITY_NAME, id.toString())).build();
    }
}
