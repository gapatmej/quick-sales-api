package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.service.InvoiceClientService;
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
 * REST controller for managing {@link ec.com.newsolutions.domain.InvoiceClient}.
 */
@RestController
@RequestMapping("/api")
public class InvoiceClientResource {

    private final Logger log = LoggerFactory.getLogger(InvoiceClientResource.class);

    private static final String ENTITY_NAME = "invoiceClient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final InvoiceClientService invoiceClientService;

    public InvoiceClientResource(InvoiceClientService invoiceClientService) {
        this.invoiceClientService = invoiceClientService;
    }

    /**
     * {@code POST  /invoice-clients} : Create a new invoiceClient.
     *
     * @param invoiceClient the invoiceClient to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new invoiceClient, or with status {@code 400 (Bad Request)} if the invoiceClient has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/invoice-clients")
    public ResponseEntity<InvoiceClient> createInvoiceClient(@Valid @RequestBody InvoiceClient invoiceClient) throws URISyntaxException {
        log.debug("REST request to save InvoiceClient : {}", invoiceClient);
        if (invoiceClient.getId() != null) {
            throw new BadRequestAlertException("A new invoiceClient cannot already have an ID", ENTITY_NAME, "idexists");
        }
        InvoiceClient result = invoiceClientService.save(invoiceClient);
        return ResponseEntity.created(new URI("/api/invoice-clients/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /invoice-clients} : Updates an existing invoiceClient.
     *
     * @param invoiceClient the invoiceClient to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated invoiceClient,
     * or with status {@code 400 (Bad Request)} if the invoiceClient is not valid,
     * or with status {@code 500 (Internal Server Error)} if the invoiceClient couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/invoice-clients")
    public ResponseEntity<InvoiceClient> updateInvoiceClient(@Valid @RequestBody InvoiceClient invoiceClient) throws URISyntaxException {
        log.debug("REST request to update InvoiceClient : {}", invoiceClient);
        if (invoiceClient.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        InvoiceClient result = invoiceClientService.save(invoiceClient);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, invoiceClient.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /invoice-clients} : get all the invoiceClients.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invoiceClients in body.
     */
    @GetMapping("/invoice-clients")
    public ResponseEntity<List<InvoiceClient>> getAllInvoiceClients(Pageable pageable) {
        log.debug("REST request to get a page of InvoiceClients");
        Page<InvoiceClient> page = invoiceClientService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /invoice-clients/:id} : get the "id" invoiceClient.
     *
     * @param id the id of the invoiceClient to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the invoiceClient, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/invoice-clients/{id}")
    public ResponseEntity<InvoiceClient> getInvoiceClient(@PathVariable Long id) {
        log.debug("REST request to get InvoiceClient : {}", id);
        Optional<InvoiceClient> invoiceClient = invoiceClientService.findOne(id);
        return ResponseUtil.wrapOrNotFound(invoiceClient);
    }

    /**
     * {@code DELETE  /invoice-clients/:id} : delete the "id" invoiceClient.
     *
     * @param id the id of the invoiceClient to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/invoice-clients/{id}")
    public ResponseEntity<Void> deleteInvoiceClient(@PathVariable Long id) {
        log.debug("REST request to delete InvoiceClient : {}", id);
        invoiceClientService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
