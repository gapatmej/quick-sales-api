package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.domain.Cellar;
import ec.com.newsolutions.service.CellarService;
import ec.com.newsolutions.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ec.com.newsolutions.domain.Cellar}.
 */
@RestController
@RequestMapping("/api")
public class CellarResource {

    private final Logger log = LoggerFactory.getLogger(CellarResource.class);

    private static final String ENTITY_NAME = "cellar";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final CellarService cellarService;

    public CellarResource(CellarService cellarService) {
        this.cellarService = cellarService;
    }

    /**
     * {@code POST  /cellars} : Create a new cellar.
     *
     * @param cellar the cellar to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new cellar, or with status {@code 400 (Bad Request)} if the cellar has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/cellars")
    public ResponseEntity<Cellar> createCellar(@Valid @RequestBody Cellar cellar) throws URISyntaxException {
        log.debug("REST request to save Cellar : {}", cellar);
        if (cellar.getId() != null) {
            throw new BadRequestAlertException("A new cellar cannot already have an ID", ENTITY_NAME, "idexists");
        }
        Cellar result = cellarService.save(cellar);
        return ResponseEntity.created(new URI("/api/cellars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /cellars} : Updates an existing cellar.
     *
     * @param cellar the cellar to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated cellar,
     * or with status {@code 400 (Bad Request)} if the cellar is not valid,
     * or with status {@code 500 (Internal Server Error)} if the cellar couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/cellars")
    public ResponseEntity<Cellar> updateCellar(@Valid @RequestBody Cellar cellar) throws URISyntaxException {
        log.debug("REST request to update Cellar : {}", cellar);
        if (cellar.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        Cellar result = cellarService.save(cellar);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, cellar.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cellars} : get all the cellars.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cellars in body.
     */
    @GetMapping("/cellars")
    public ResponseEntity<List<Cellar>> getAllCellars(Pageable pageable) {
        log.debug("REST request to get a page of Cellars");
        Page<Cellar> page = cellarService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /cellars/:id} : get the "id" cellar.
     *
     * @param id the id of the cellar to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the cellar, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/cellars/{id}")
    public ResponseEntity<Cellar> getCellar(@PathVariable Long id) {
        log.debug("REST request to get Cellar : {}", id);
        Optional<Cellar> cellar = cellarService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cellar);
    }

    /**
     * {@code DELETE  /cellars/:id} : delete the "id" cellar.
     *
     * @param id the id of the cellar to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/cellars/{id}")
    public ResponseEntity<Void> deleteCellar(@PathVariable Long id) {
        log.debug("REST request to delete Cellar : {}", id);
        cellarService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
