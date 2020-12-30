package ec.com.newsolutions.web.rest;


import ec.com.newsolutions.service.BankService;
import ec.com.newsolutions.service.dto.BankDTO;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class BankResource extends AbstractResource{

    private final BankService bankService;

    public BankResource(BankService bankService) {
        super(BankService.class, "bank");
        this.bankService = bankService;
    }

    @PostMapping("/banks")
    public ResponseEntity<BankDTO> create(@Valid @RequestBody BankDTO bankDTO) throws URISyntaxException {
        log.debug("REST request to save Bank : {}", GsonUtils.entityToJson(bankDTO));
        if (bankDTO.getId() != null) throw new IdExistException(entityName);

        BankDTO result = bankService.save(bankDTO);
        return ResponseEntity.created(new URI("/api/banks/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(true, entityName, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/banks")
    public ResponseEntity<BankDTO> update(@Valid @RequestBody BankDTO bankDTO) throws URISyntaxException {
        log.debug("REST request to update Bank : {}", GsonUtils.entityToJson(bankDTO));
        if (bankDTO.getId() == null) throw new InvalidIdException(entityName);

        BankDTO result = bankService.save(bankDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(true, entityName, bankDTO.getId().toString()))
            .body(result);
    }

    @GetMapping("/banks")
    public ResponseEntity<List<BankDTO>> getAll(String search, Pageable pageable) {
        log.debug("REST request to get a page of Banks");
        Page<BankDTO> page = bankService.findAll(search, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/banks/{id}")
    public ResponseEntity<BankDTO> get(@PathVariable Long id) {
        log.debug("REST request to get Bank : {}", id);
        Optional<BankDTO> bankDTO = bankService.findOneDto(id);
        return ResponseUtil.wrapOrNotFound(bankDTO);
    }

    @DeleteMapping("/banks/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete Bank : {}", id);
        bankService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(true, entityName, id.toString())).build();
    }
}
