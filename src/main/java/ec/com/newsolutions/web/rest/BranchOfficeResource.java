package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.domain.BranchOffice;
import ec.com.newsolutions.service.BranchOfficeService;
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
 * REST controller for managing {@link ec.com.newsolutions.domain.BranchOffice}.
 */
@RestController
@RequestMapping("/api")
public class BranchOfficeResource {

    private final Logger log = LoggerFactory.getLogger(BranchOfficeResource.class);

    private static final String ENTITY_NAME = "branchOffice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final BranchOfficeService branchOfficeService;

    public BranchOfficeResource(BranchOfficeService branchOfficeService) {
        this.branchOfficeService = branchOfficeService;
    }

    /**
     * {@code POST  /branch-offices} : Create a new branchOffice.
     *
     * @param branchOffice the branchOffice to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new branchOffice, or with status {@code 400 (Bad Request)} if the branchOffice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/branch-offices")
    public ResponseEntity<BranchOffice> createBranchOffice(@Valid @RequestBody BranchOffice branchOffice) throws URISyntaxException {
        log.debug("REST request to save BranchOffice : {}", branchOffice);
        if (branchOffice.getId() != null) {
            throw new BadRequestAlertException("A new branchOffice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        BranchOffice result = branchOfficeService.save(branchOffice);
        return ResponseEntity.created(new URI("/api/branch-offices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /branch-offices} : Updates an existing branchOffice.
     *
     * @param branchOffice the branchOffice to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated branchOffice,
     * or with status {@code 400 (Bad Request)} if the branchOffice is not valid,
     * or with status {@code 500 (Internal Server Error)} if the branchOffice couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/branch-offices")
    public ResponseEntity<BranchOffice> updateBranchOffice(@Valid @RequestBody BranchOffice branchOffice) throws URISyntaxException {
        log.debug("REST request to update BranchOffice : {}", branchOffice);
        if (branchOffice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        BranchOffice result = branchOfficeService.save(branchOffice);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, branchOffice.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /branch-offices} : get all the branchOffices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of branchOffices in body.
     */
    @GetMapping("/branch-offices")
    public ResponseEntity<List<BranchOffice>> getAllBranchOffices(Pageable pageable) {
        log.debug("REST request to get a page of BranchOffices");
        Page<BranchOffice> page = branchOfficeService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /branch-offices/:id} : get the "id" branchOffice.
     *
     * @param id the id of the branchOffice to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the branchOffice, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/branch-offices/{id}")
    public ResponseEntity<BranchOffice> getBranchOffice(@PathVariable Long id) {
        log.debug("REST request to get BranchOffice : {}", id);
        Optional<BranchOffice> branchOffice = branchOfficeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(branchOffice);
    }

    /**
     * {@code DELETE  /branch-offices/:id} : delete the "id" branchOffice.
     *
     * @param id the id of the branchOffice to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/branch-offices/{id}")
    public ResponseEntity<Void> deleteBranchOffice(@PathVariable Long id) {
        log.debug("REST request to delete BranchOffice : {}", id);
        branchOfficeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
