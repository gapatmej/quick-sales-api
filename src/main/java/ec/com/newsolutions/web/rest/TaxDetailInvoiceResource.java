package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.domain.TaxDetailInvoice;
import ec.com.newsolutions.service.TaxDetailInvoiceService;
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
 * REST controller for managing {@link ec.com.newsolutions.domain.TaxDetailInvoice}.
 */
@RestController
@RequestMapping("/api")
public class TaxDetailInvoiceResource {

    private final Logger log = LoggerFactory.getLogger(TaxDetailInvoiceResource.class);

    private static final String ENTITY_NAME = "taxDetailInvoice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaxDetailInvoiceService taxDetailInvoiceService;

    public TaxDetailInvoiceResource(TaxDetailInvoiceService taxDetailInvoiceService) {
        this.taxDetailInvoiceService = taxDetailInvoiceService;
    }

    /**
     * {@code POST  /tax-detail-invoices} : Create a new taxDetailInvoice.
     *
     * @param taxDetailInvoice the taxDetailInvoice to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taxDetailInvoice, or with status {@code 400 (Bad Request)} if the taxDetailInvoice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tax-detail-invoices")
    public ResponseEntity<TaxDetailInvoice> createTaxDetailInvoice(@Valid @RequestBody TaxDetailInvoice taxDetailInvoice) throws URISyntaxException {
        log.debug("REST request to save TaxDetailInvoice : {}", taxDetailInvoice);
        if (taxDetailInvoice.getId() != null) {
            throw new BadRequestAlertException("A new taxDetailInvoice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaxDetailInvoice result = taxDetailInvoiceService.save(taxDetailInvoice);
        return ResponseEntity.created(new URI("/api/tax-detail-invoices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tax-detail-invoices} : Updates an existing taxDetailInvoice.
     *
     * @param taxDetailInvoice the taxDetailInvoice to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taxDetailInvoice,
     * or with status {@code 400 (Bad Request)} if the taxDetailInvoice is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taxDetailInvoice couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tax-detail-invoices")
    public ResponseEntity<TaxDetailInvoice> updateTaxDetailInvoice(@Valid @RequestBody TaxDetailInvoice taxDetailInvoice) throws URISyntaxException {
        log.debug("REST request to update TaxDetailInvoice : {}", taxDetailInvoice);
        if (taxDetailInvoice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TaxDetailInvoice result = taxDetailInvoiceService.save(taxDetailInvoice);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taxDetailInvoice.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tax-detail-invoices} : get all the taxDetailInvoices.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taxDetailInvoices in body.
     */
    @GetMapping("/tax-detail-invoices")
    public ResponseEntity<List<TaxDetailInvoice>> getAllTaxDetailInvoices(Pageable pageable) {
        log.debug("REST request to get a page of TaxDetailInvoices");
        Page<TaxDetailInvoice> page = taxDetailInvoiceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /tax-detail-invoices/:id} : get the "id" taxDetailInvoice.
     *
     * @param id the id of the taxDetailInvoice to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taxDetailInvoice, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tax-detail-invoices/{id}")
    public ResponseEntity<TaxDetailInvoice> getTaxDetailInvoice(@PathVariable Long id) {
        log.debug("REST request to get TaxDetailInvoice : {}", id);
        Optional<TaxDetailInvoice> taxDetailInvoice = taxDetailInvoiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taxDetailInvoice);
    }

    /**
     * {@code DELETE  /tax-detail-invoices/:id} : delete the "id" taxDetailInvoice.
     *
     * @param id the id of the taxDetailInvoice to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tax-detail-invoices/{id}")
    public ResponseEntity<Void> deleteTaxDetailInvoice(@PathVariable Long id) {
        log.debug("REST request to delete TaxDetailInvoice : {}", id);
        taxDetailInvoiceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
