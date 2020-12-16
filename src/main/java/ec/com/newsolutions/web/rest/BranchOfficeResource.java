package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.service.BranchOfficeService;
import ec.com.newsolutions.service.dto.BranchOfficeDTO;

import ec.com.newsolutions.utils.GsonUtils;
import ec.com.newsolutions.web.rest.errors.IdExistException;
import ec.com.newsolutions.web.rest.errors.InvalidIdException;
import ec.com.newsolutions.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
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
public class BranchOfficeResource extends AbstractResource {

    private final BranchOfficeService branchOfficeService;

    public BranchOfficeResource(BranchOfficeService branchOfficeService) {
        super(BranchOfficeResource.class,"branchOffice");
        this.branchOfficeService = branchOfficeService;
    }

    @PostMapping("/branch-offices")
    public ResponseEntity<BranchOfficeDTO> createBranchOffice(@Valid @RequestBody BranchOfficeDTO branchOfficeDTO) throws URISyntaxException {
        log.debug("REST request to save BranchOffice : {}", GsonUtils.entityToJson(branchOfficeDTO));
        if (branchOfficeDTO.getId() != null) throw new IdExistException(entityName);

        BranchOfficeDTO result = branchOfficeService.save(branchOfficeDTO);
        return ResponseEntity.created(new URI("/api/branch-offices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(true, entityName, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/branch-offices")
    public ResponseEntity<BranchOfficeDTO> updateBranchOffice(@Valid @RequestBody BranchOfficeDTO branchOfficeDTO) throws URISyntaxException {
        log.debug("REST request to update BranchOffice : {}", GsonUtils.entityToJson(branchOfficeDTO));
        if (branchOfficeDTO.getId() == null) throw new InvalidIdException(entityName);

        BranchOfficeDTO result = branchOfficeService.save(branchOfficeDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(true, entityName, result.getId().toString()))
            .body(result);
    }


    @GetMapping("/branch-offices")
    public ResponseEntity<List<BranchOfficeDTO>> getAllBranchOffices(String search, Pageable pageable) {
        log.debug("REST request to get a page of BranchOffices");
        Page<BranchOfficeDTO> page = branchOfficeService.findAll(search, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    @GetMapping("/branch-offices/withoutWorkspace")
    public ResponseEntity<List<BranchOfficeDTO>> getAllBranchOfficesWithoutWorkspace(String search, Pageable pageable) {
        log.debug("REST request to get a page of BranchOffices");
        Page<BranchOfficeDTO> page = branchOfficeService.findAllWithoutWorkspace(search, pageable);
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
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(true, entityName, id.toString())).build();
    }
}
