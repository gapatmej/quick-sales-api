package ec.com.newsolutions.web.rest;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class DetailInvoiceResource {

   /* private final Logger log = LoggerFactory.getLogger(DetailInvoiceResource.class);

    private static final String ENTITY_NAME = "detailInvoice";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final DetailInvoiceService detailInvoiceService;

    public DetailInvoiceResource(DetailInvoiceService detailInvoiceService) {
        this.detailInvoiceService = detailInvoiceService;
    }


    @PostMapping("/detail-invoices")
    public ResponseEntity<DetailInvoiceClient> createDetailInvoice(@Valid @RequestBody DetailInvoiceClient detailInvoice) throws URISyntaxException {
        log.debug("REST request to save DetailInvoice : {}", detailInvoice);
        if (detailInvoice.getId() != null) {
            throw new BadRequestAlertException("A new detailInvoice cannot already have an ID", ENTITY_NAME, "idexists");
        }
        DetailInvoiceClient result = detailInvoiceService.save(detailInvoice);
        return ResponseEntity.created(new URI("/api/detail-invoices/" + result.getId()))
            .headers(HeaderUtil.createEntityCreationAlert(applicationName, true, ENTITY_NAME, result.getId().toString()))
            .body(result);
    }


    @PutMapping("/detail-invoices")
    public ResponseEntity<DetailInvoiceClient> updateDetailInvoice(@Valid @RequestBody DetailInvoiceClient detailInvoice) throws URISyntaxException {
        log.debug("REST request to update DetailInvoice : {}", detailInvoice);
        if (detailInvoice.getId() == null) {
            throw new BadRequestAlertException("Invalid id", ENTITY_NAME, "idnull");
        }
        DetailInvoiceClient result = detailInvoiceService.save(detailInvoice);
        return ResponseEntity.ok()
            .headers(HeaderUtil.createEntityUpdateAlert(applicationName, true, ENTITY_NAME, detailInvoice.getId().toString()))
            .body(result);
    }


    @GetMapping("/detail-invoices")
    public ResponseEntity<List<DetailInvoice>> getAllDetailInvoices(Pageable pageable) {
        log.debug("REST request to get a page of DetailInvoices");
        Page<DetailInvoice> page = detailInvoiceService.findAll(pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }


    @GetMapping("/detail-invoices/{id}")
    public ResponseEntity<DetailInvoice> getDetailInvoice(@PathVariable Long id) {
        log.debug("REST request to get DetailInvoice : {}", id);
        Optional<DetailInvoice> detailInvoice = detailInvoiceService.findOne(id);
        return ResponseUtil.wrapOrNotFound(detailInvoice);
    }

    @DeleteMapping("/detail-invoices/{id}")
    public ResponseEntity<Void> deleteDetailInvoice(@PathVariable Long id) {
        log.debug("REST request to delete DetailInvoice : {}", id);
        detailInvoiceService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(applicationName, true, ENTITY_NAME, id.toString())).build();
    }*/
}
