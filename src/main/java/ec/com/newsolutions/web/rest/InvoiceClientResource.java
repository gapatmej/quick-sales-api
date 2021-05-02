package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.service.InvoiceClientService;
import ec.com.newsolutions.service.dto.InvoiceClientDTO;
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
public class InvoiceClientResource extends AbstractResource {

    private final InvoiceClientService invoiceClientService;

    public InvoiceClientResource(InvoiceClientService invoiceClientService) {
        super(InvoiceClientService.class,"invoiceClient");
        this.invoiceClientService = invoiceClientService;
    }

    @PostMapping("/invoices-client")
    public ResponseEntity<InvoiceClientDTO> create(@Valid @RequestBody InvoiceClientDTO invoiceClientDTO) throws URISyntaxException {
        log.debug("REST request to save InvoiceClientDTO : {}", GsonUtils.entityToJson(invoiceClientDTO));
        if (invoiceClientDTO.getId() != null) throw new IdExistException(entityName);

        InvoiceClientDTO result = invoiceClientService.save(invoiceClientDTO);
        return ResponseEntity.created(new URI("/api/invoices-client/" + result.getId()))
            .headers(ec.com.newsolutions.web.rest.util.HeaderUtil.createEntityCreationAlert(true, entityName, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/invoices-client")
    public ResponseEntity<InvoiceClientDTO> update(@Valid @RequestBody InvoiceClientDTO invoiceClientDTO) throws URISyntaxException {
        log.debug("REST request to update InvoiceClientDTO : {}", GsonUtils.entityToJson(invoiceClientDTO));
        if (invoiceClientDTO.getId() == null) throw new InvalidIdException(entityName);

        InvoiceClientDTO result = invoiceClientService.save(invoiceClientDTO);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityCreationAlert(true, entityName, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/invoices-client/calculate-totals")
    public ResponseEntity<InvoiceClientDTO> calculateTotals(@Valid @RequestBody InvoiceClientDTO invoiceClientDTO) throws URISyntaxException {
        log.debug("REST request to calculate totals InvoiceClientDTO : {}", GsonUtils.entityToJson(invoiceClientDTO));

        InvoiceClientDTO result = invoiceClientService.calculateTotals(invoiceClientDTO);
        return ResponseEntity.ok()
            .body(result);
    }

    @GetMapping("/invoices-client")
    public ResponseEntity<List<InvoiceClientDTO>> getAll(String search, Pageable pageable) {
        log.debug("REST request to get a page of InvoicesClient");
        Page<InvoiceClientDTO> page = invoiceClientService.findAll(search, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/invoices-client/{id}")
    public ResponseEntity<InvoiceClientDTO> get(@PathVariable Long id) {
        log.debug("REST request to get InvoiceClientDTO : {}", id);
        Optional<InvoiceClientDTO> result = invoiceClientService.findOneDto(id);
        return ResponseUtil.wrapOrNotFound(result);
    }

    @DeleteMapping("/invoices-client/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete id : {}", id);
        invoiceClientService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(true, entityName, id.toString())).build();
    }
}
