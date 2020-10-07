package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.service.OrganizationService;
import ec.com.newsolutions.service.dto.OrganizationDTO;
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
public class OrganizationResource {

    private final Logger log = LoggerFactory.getLogger(OrganizationResource.class);

    private static final String ENTITY_NAME = "organization";
    private final OrganizationService organizationService;

    public OrganizationResource(OrganizationService organizationService) {
        this.organizationService = organizationService;
    }

    @PostMapping("/organizations")
    public ResponseEntity<OrganizationDTO> createOrganization(@Valid @RequestBody OrganizationDTO organizationDTO) throws URISyntaxException {
        log.debug("REST request to save Organization : {}", organizationDTO);
        if (organizationDTO.getId() != null) throw new IdExistException(ENTITY_NAME);

        OrganizationDTO result = organizationService.save(organizationDTO);
        return ResponseEntity.created(new URI("/api/organizations/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/organizations")
    public ResponseEntity<OrganizationDTO> updateOrganization(@Valid @RequestBody OrganizationDTO organizationDTO) throws URISyntaxException {
        log.debug("REST request to update Organization : {}", organizationDTO);
        if (organizationDTO.getId() == null) throw new InvalidIdException(ENTITY_NAME);

        OrganizationDTO result = organizationService.save(organizationDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    @GetMapping("/organizations")
    public ResponseEntity<List<OrganizationDTO>> getAllOrganizations(String search, Pageable pageable) {
        log.debug("REST request to get a page of Organizations");
        Page<OrganizationDTO> page = organizationService.findAll(search, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/organizations/{id}")
    public ResponseEntity<OrganizationDTO> getOrganization(@PathVariable Long id) {
        log.debug("REST request to get Organization : {}", id);
        Optional<OrganizationDTO> organizationDTO = organizationService.findOne(id);
        return ResponseUtil.wrapOrNotFound(organizationDTO);
    }

    @DeleteMapping("/organizations/{id}")
    public ResponseEntity<Void> deleteOrganization(@PathVariable Long id) {
        log.debug("REST request to delete Organization : {}", id);
        organizationService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(true, ENTITY_NAME, id.toString())).build();
    }
}
