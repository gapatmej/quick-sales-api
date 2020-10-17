package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.domain.BranchOffice;
import ec.com.newsolutions.service.BranchOfficeService;
import ec.com.newsolutions.service.dto.BranchOfficeDTO;
import ec.com.newsolutions.web.rest.errors.BadRequestAlertException;

import ec.com.newsolutions.web.rest.errors.IdExistException;
import ec.com.newsolutions.web.rest.errors.InvalidIdException;
import ec.com.newsolutions.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
public class BranchOfficeResource {

    private final Logger log = LoggerFactory.getLogger(BranchOfficeResource.class);

    private static final String ENTITY_NAME = "branchOffice";
    private final BranchOfficeService branchOfficeService;

    public BranchOfficeResource(BranchOfficeService branchOfficeService) {
        this.branchOfficeService = branchOfficeService;
    }

    @PostMapping("/branch-offices")
    public ResponseEntity<BranchOfficeDTO> createBranchOffice(@Valid @RequestBody BranchOfficeDTO branchOfficeDTO) throws URISyntaxException {
        log.debug("REST request to save BranchOffice : {}", branchOfficeDTO);
        if (branchOfficeDTO.getId() != null) throw new IdExistException(ENTITY_NAME);

        BranchOfficeDTO result = branchOfficeService.save(branchOfficeDTO);
        return ResponseEntity.created(new URI("/api/organizations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/branch-offices")
    public ResponseEntity<BranchOfficeDTO> updateBranchOffice(@Valid @RequestBody BranchOfficeDTO branchOfficeDTO) throws URISyntaxException {
        log.debug("REST request to update BranchOffice : {}", branchOfficeDTO);
        if (branchOfficeDTO.getId() == null) throw new InvalidIdException(ENTITY_NAME);

        BranchOfficeDTO result = branchOfficeService.save(branchOfficeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    @GetMapping("/branch-offices")
    public ResponseEntity<List<BranchOfficeDTO>> getAllBranchOffices(String search, Pageable pageable) {
        log.debug("REST request to get a page of BranchOffices");
        Page<BranchOfficeDTO> page = branchOfficeService.findAll(search, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/branch-offices/{id}")
    public ResponseEntity<BranchOfficeDTO> getBranchOffice(@PathVariable Long id) {
        log.debug("REST request to get BranchOffice : {}", id);
        Optional<BranchOfficeDTO> branchOfficeDTO = branchOfficeService.findOne(id);
        return ResponseUtil.wrapOrNotFound(branchOfficeDTO);
    }

    @DeleteMapping("/branch-offices/{id}")
    public ResponseEntity<Void> deleteBranchOffice(@PathVariable Long id) {
        log.debug("REST request to delete BranchOffice : {}", id);
        branchOfficeService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(true, ENTITY_NAME, id.toString())).build();
    }
}
