package ec.com.newsolutions.web.rest;


import ec.com.newsolutions.service.CompanyCategoryService;
import ec.com.newsolutions.service.dto.CompanyCategoryDTO;
import ec.com.newsolutions.utils.GsonUtils;
import ec.com.newsolutions.web.rest.errors.IdExistException;
import ec.com.newsolutions.web.rest.errors.InvalidIdException;
import ec.com.newsolutions.web.rest.util.HeaderUtil;
import io.github.jhipster.web.util.PaginationUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CompanyCategoryResource extends AbstractResource {

    private final CompanyCategoryService companyCategoryService;

    public CompanyCategoryResource(CompanyCategoryService companyCategoryService) {
        super(CompanyCategoryResource.class,"companyCategory");
        this.companyCategoryService = companyCategoryService;
    }

    @PostMapping("/company-categories")
    public ResponseEntity<CompanyCategoryDTO> create(@Valid @RequestBody CompanyCategoryDTO companyCategoryDTO) throws URISyntaxException {
        log.debug("REST request to save CompanyCategory : {}", GsonUtils.entityToJson(companyCategoryDTO));
        if (companyCategoryDTO.getId() != null) throw new IdExistException(entityName);

        CompanyCategoryDTO result = companyCategoryService.save(companyCategoryDTO);
        return ResponseEntity.created(new URI("/api/company-categories/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(true, entityName, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/company-categories")
    public ResponseEntity<CompanyCategoryDTO> update(@Valid @RequestBody CompanyCategoryDTO companyCategoryDTO) throws URISyntaxException {
        log.debug("REST request to update CompanyCategory : {}", GsonUtils.entityToJson(companyCategoryDTO));
        if (companyCategoryDTO.getId() == null) throw new InvalidIdException(entityName);

        CompanyCategoryDTO result = companyCategoryService.save(companyCategoryDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(true, entityName, result.getId().toString()))
            .body(result);
    }


    @GetMapping("/company-categories")
    public ResponseEntity<List<CompanyCategoryDTO>> getAll(String search, Pageable pageable) {
        log.debug("REST request to get a page of CompanyCategory");
        Page<CompanyCategoryDTO> page = companyCategoryService.findAll(search, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/company-categories/{id}")
    public ResponseEntity<CompanyCategoryDTO> get(@PathVariable Long id) {
        log.debug("REST request to get CompanyCategory : {}", id);
        Optional<CompanyCategoryDTO> companyCategoryDTO = companyCategoryService.findOneDto(id);
        return ResponseUtil.wrapOrNotFound(companyCategoryDTO);
    }

    @DeleteMapping("/company-categories/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete CompanyCategory : {}", id);
        companyCategoryService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(true, entityName, id.toString())).build();
    }
}
