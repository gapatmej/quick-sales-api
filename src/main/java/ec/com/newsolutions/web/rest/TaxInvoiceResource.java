package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.domain.TaxInvoice;
import ec.com.newsolutions.service.TaxInvoiceService;
import ec.com.newsolutions.web.rest.errors.BadRequestAlertException;

import io.github.jhipster.web.util.HeaderUtil;
import io.github.jhipster.web.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Optional;

/**
 * REST controller for managing {@link ec.com.newsolutions.domain.TaxInvoice}.
 */
@RestController
@RequestMapping("/api")
public class TaxInvoiceResource {

    private final Logger log = LoggerFactory.getLogger(TaxInvoiceResource.class);

    private static final String ENTITY_NAME = "taxInvoice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final TaxInvoiceService taxInvoiceService;

    public TaxInvoiceResource(TaxInvoiceService taxInvoiceService) {
        this.taxInvoiceService = taxInvoiceService;
    }

    /**
     * {@code POST  /tax-invoices} : Create a new taxInvoice.
     *
     * @param taxInvoice the taxInvoice to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new taxInvoice, or with status {@code 400 (Bad Request)} if the taxInvoice has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/tax-invoices")
    public ResponseEntity<TaxInvoice> createTaxInvoice(@Valid @RequestBody TaxInvoice taxInvoice) throws URISyntaxException {
        log.debug("REST request to save TaxInvoice : {}", taxInvoice);
        if (taxInvoice.getId() != null) {
            throw new BadRequestAlertException("A new taxInvoice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        TaxInvoice result = taxInvoiceService.save(taxInvoice);
        return ResponseEntity.created(new URI("/api/tax-invoices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /tax-invoices} : Updates an existing taxInvoice.
     *
     * @param taxInvoice the taxInvoice to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated taxInvoice,
     * or with status {@code 400 (Bad Request)} if the taxInvoice is not valid,
     * or with status {@code 500 (Internal Server Error)} if the taxInvoice couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/tax-invoices")
    public ResponseEntity<TaxInvoice> updateTaxInvoice(@Valid @RequestBody TaxInvoice taxInvoice) throws URISyntaxException {
        log.debug("REST request to update TaxInvoice : {}", taxInvoice);
        if (taxInvoice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        TaxInvoice result = taxInvoiceService.save(taxInvoice);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, taxInvoice.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /tax-invoices} : get all the taxInvoices.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of taxInvoices in body.
     */
    @GetMapping("/tax-invoices")
    public List<TaxInvoice> getAllTaxInvoices() {
        log.debug("REST request to get all TaxInvoices");
        return taxInvoiceService.findAll();
    }

    /**
     * {@code GET  /tax-invoices/:id} : get the "id" taxInvoice.
     *
     * @param id the id of the taxInvoice to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the taxInvoice, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/tax-invoices/{id}")
    public ResponseEntity<TaxInvoice> getTaxInvoice(@PathVariable Long id) {
        log.debug("REST request to get TaxInvoice : {}", id);
        Optional<TaxInvoice> taxInvoice = taxInvoiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(taxInvoice);
    }

    /**
     * {@code DELETE  /tax-invoices/:id} : delete the "id" taxInvoice.
     *
     * @param id the id of the taxInvoice to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/tax-invoices/{id}")
    public ResponseEntity<Void> deleteTaxInvoice(@PathVariable Long id) {
        log.debug("REST request to delete TaxInvoice : {}", id);
        taxInvoiceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
