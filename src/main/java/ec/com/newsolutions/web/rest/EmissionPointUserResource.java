package ec.com.newsolutions.web.rest;


import ec.com.newsolutions.domain.EmissionPointUser;
import ec.com.newsolutions.service.EmissionPointUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class EmissionPointUserResource {

    private final Logger log = LoggerFactory.getLogger(EmissionPointUser.class);

    private static final String ENTITY_NAME = "invoiceClient";

    @Value("${jhipster.clientApp.name}")
    private String applicationName;

    private final EmissionPointUserService emissionPointUserService;

    public EmissionPointUserResource(EmissionPointUserService emissionPointUserService) {
        this.emissionPointUserService = emissionPointUserService;
    }

    /**
     * {@code GET  /invoice-clients} : get all the invoiceClients.
     *
     * @return the {@link ResponseEntity} with status {@code 200 (OK)} and the list of invoiceClients in body.
     */
    @GetMapping("/emission-point-user/{login}")
    public ResponseEntity<List<EmissionPointUser>> getAllEmissionPointByUser(@PathVariable String login) {
    log.debug("REST request to get a page of InvoiceClients");
    List<EmissionPointUser> emissionPointUsers = emissionPointUserService.findAllByUser(login);
    return ResponseEntity.ok().body(emissionPointUsers);
    }
}
