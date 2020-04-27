package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.domain.PayWay;
import ec.com.newsolutions.service.PayWayService;
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
 * REST controller for managing {@link ec.com.newsolutions.domain.PayWay}.
 */
@RestController
@RequestMapping("/api")
public class PayWayResource {

    private final Logger log = LoggerFactory.getLogger(PayWayResource.class);

    private static final String ENTITY_NAME = "payWay";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final PayWayService payWayService;

    public PayWayResource(PayWayService payWayService) {
        this.payWayService = payWayService;
    }

    /**
     * {@code POST  /pay-ways} : Create a new payWay.
     *
     * @param payWay the payWay to create.
     * @return the {@link ResponseEntity} with status {@code 201 (Created)} and with body the new payWay, or with status {@code 400 (Bad Request)} if the payWay has already an ID.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PostMapping("/pay-ways")
    public ResponseEntity<PayWay> createPayWay(@Valid @RequestBody PayWay payWay) throws URISyntaxException {
        log.debug("REST request to save PayWay : {}", payWay);
        if (payWay.getId() != null) {
            throw new BadRequestAlertException("A new payWay cannot already have an ID", ENTITY_NAME, "idexists");
        }
        PayWay result = payWayService.save(payWay);
        return ResponseEntity.created(new URI("/api/pay-ways/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }

    /**
     * {@code PUT  /pay-ways} : Updates an existing payWay.
     *
     * @param payWay the payWay to update.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the updated payWay,
     * or with status {@code 400 (Bad Request)} if the payWay is not valid,
     * or with status {@code 500 (Internal Server Error)} if the payWay couldn't be updated.
     * @throws URISyntaxException if the Location URI syntax is incorrect.
     */
    @PutMapping("/pay-ways")
    public ResponseEntity<PayWay> updatePayWay(@Valid @RequestBody PayWay payWay) throws URISyntaxException {
        log.debug("REST request to update PayWay : {}", payWay);
        if (payWay.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        PayWay result = payWayService.save(payWay);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, payWay.getId().toString()))
            .body(result);
    }

    /**
     * {@code GET  /pay-ways} : get all the payWays.
     *
     * @param pageable the pagination information.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of payWays in body.
     */
    @GetMapping("/pay-ways")
    public ResponseEntity<List<PayWay>> getAllPayWays(Pageable pageable) {
        log.debug("REST request to get a page of PayWays");
        Page<PayWay> page = payWayService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    /**
     * {@code GET  /pay-ways/:id} : get the "id" payWay.
     *
     * @param id the id of the payWay to retrieve.
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and with body the payWay, or with status {@code 404 (Not Found)}.
     */
    @GetMapping("/pay-ways/{id}")
    public ResponseEntity<PayWay> getPayWay(@PathVariable Long id) {
        log.debug("REST request to get PayWay : {}", id);
        Optional<PayWay> payWay = payWayService.findOne(id);
        return ResponseUtil.wrapOrNotFound(payWay);
    }

    /**
     * {@code DELETE  /pay-ways/:id} : delete the "id" payWay.
     *
     * @param id the id of the payWay to delete.
     * @return the {@link ResponseEntity} with status {@code 204 (NO_CONTENT)}.
     */
    @DeleteMapping("/pay-ways/{id}")
    public ResponseEntity<Void> deletePayWay(@PathVariable Long id) {
        log.debug("REST request to delete PayWay : {}", id);
        payWayService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }
}
