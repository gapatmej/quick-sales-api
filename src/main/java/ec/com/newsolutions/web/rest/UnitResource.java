package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.service.UnitService;
import ec.com.newsolutions.service.dto.UnitDTO;
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
public class UnitResource {

    private final Logger log = LoggerFactory.getLogger(UnitResource.class);
    private static final String ENTITY_NAME = "unit";

    private final UnitService unitService;

    public UnitResource(UnitService unitService) {
        this.unitService = unitService;
    }

    @PostMapping("/units")
    public ResponseEntity<UnitDTO> createUnit(@Valid @RequestBody UnitDTO unitDTO) throws URISyntaxException {
        log.debug("REST request to save Unit : {}", unitDTO);
        if (unitDTO.getId() != null) throw new IdExistException(ENTITY_NAME);

        UnitDTO result = unitService.save(unitDTO);
        return ResponseEntity.created(new URI("/api/units/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/units")
    public ResponseEntity<UnitDTO> updateUnit(@Valid @RequestBody UnitDTO unitDTO) throws URISyntaxException {
        log.debug("REST request to update Unit : {}", unitDTO);
        if (unitDTO.getId() == null) throw new InvalidIdException(ENTITY_NAME);

        UnitDTO result = unitService.save(unitDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(true, ENTITY_NAME, unitDTO.getId().toString()))
            .body(result);
    }

    @GetMapping("/units")
    public ResponseEntity<List<UnitDTO>> getAllUnits(String search, Pageable pageable) {
        log.debug("REST request to get a page of Units");
        Page<UnitDTO> page = unitService.findAll(search, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/units/{id}")
    public ResponseEntity<UnitDTO> getUnit(@PathVariable Long id) {
        log.debug("REST request to get Unit : {}", id);
        Optional<UnitDTO> Unit = unitService.findOne(id);
        return ResponseUtil.wrapOrNotFound(Unit);
    }

    @DeleteMapping("/units/{id}")
    public ResponseEntity<Void> deleteUnit(@PathVariable Long id) {
        log.debug("REST request to delete Unit : {}", id);
        unitService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(true, ENTITY_NAME, id.toString())).build();
    }
}
