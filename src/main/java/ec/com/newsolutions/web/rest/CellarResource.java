package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.domain.Cellar;
import ec.com.newsolutions.service.CellarService;
import ec.com.newsolutions.service.dto.CellarDTO;
import ec.com.newsolutions.web.rest.errors.BadRequestAlertException;

//import io.github.jhipster.web.util.HeaderUtil;
import ec.com.newsolutions.web.rest.util.HeaderUtil;
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
import ec.com.newsolutions.web.rest.util.HeaderUtil.*;

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
    public ResponseEntity<CellarDTO> createCellar(@Valid @RequestBody CellarDTO cellarDTO) throws URISyntaxException {
        log.debug("REST request to save Cellar : {}", cellarDTO);
        if (cellarDTO.getId() != null) {
            throw new BadRequestAlertException("A new cellar cannot already have an ID", ENTITY_NAME, "idexists");
        }
        CellarDTO result = cellarService.save(cellarDTO);
        return ResponseEntity.created(new URI("/api/cellars/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(true, ENTITY_NAME, result.getId().toString()))
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
    public ResponseEntity<CellarDTO> updateCellar(@Valid @RequestBody CellarDTO cellarDTO) throws URISyntaxException {
        log.debug("REST request to update Cellar : {}", cellarDTO);
        if (cellarDTO.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        CellarDTO result = cellarService.save(cellarDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(true, ENTITY_NAME, cellarDTO.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /cellars} : get all the cellars.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of cellars in body.
     */
    @GetMapping("/cellars")
    public ResponseEntity<List<CellarDTO>> getAllCellars(String search, Pageable pageable) {
        log.debug("REST request to get a page of Cellars");
        Page<CellarDTO> page = cellarService.findAll(search, pageable);
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
    public ResponseEntity<CellarDTO> getCellar(@PathVariable Long id) {
        log.debug("REST request to get Cellar : {}", id);
        Optional<CellarDTO> cellarDTO = cellarService.findOne(id);
        return ResponseUtil.wrapOrNotFound(cellarDTO);
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
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(true, ENTITY_NAME, id.toString())).build();
    }
}
