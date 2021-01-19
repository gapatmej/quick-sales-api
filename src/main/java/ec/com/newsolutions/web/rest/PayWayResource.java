package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.domain.PayWay;
import ec.com.newsolutions.service.PayWayService;
import ec.com.newsolutions.service.PaymentService;
import ec.com.newsolutions.service.dto.PayWayDTO;
import ec.com.newsolutions.utils.GsonUtils;
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
import org.springframework.http.HttpStatus;
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
public class PayWayResource extends AbstractResource {
    private final PayWayService payWayService;

    public PayWayResource(PayWayService payWayService) {
        super(PayWayResource.class, "payWay");
        this.payWayService = payWayService;
    }

    @PostMapping("/pay-ways")
    public ResponseEntity<PayWayDTO> create(@Valid @RequestBody PayWayDTO payWayDTO) throws URISyntaxException {
        log.debug("REST request to save PayWay : {}", GsonUtils.entityToJson(payWayDTO));
        if (payWayDTO.getId() != null) throw new IdExistException(entityName);

        PayWayDTO result = payWayService.save(payWayDTO);
        return ResponseEntity.created(new URI("/api/pay-way/" + result.getId()))
            .headers(ec.com.newsolutions.web.rest.util.HeaderUtil.createEntityCreationAlert(true, entityName, result.getId().toString()))
            .body(result);
    }

    @PutMapping("/pay-ways")
    public ResponseEntity<PayWayDTO> update(@Valid @RequestBody PayWayDTO payWayDTO) throws URISyntaxException {
        log.debug("REST request to update PayWay : {}", GsonUtils.entityToJson(payWayDTO));
        if (payWayDTO.getId() == null) throw new InvalidIdException(entityName);

        PayWayDTO result = payWayService.save(payWayDTO);
        return ResponseEntity.ok()
            .headers(ec.com.newsolutions.web.rest.util.HeaderUtil.createEntityUpdateAlert(true, entityName, payWayDTO.getId().toString()))
            .body(result);
    }

    @GetMapping("/pay-ways")
    public ResponseEntity<List<PayWayDTO>> getAll(String search, Pageable pageable) {
        log.debug("REST request to get a page of PayWays");
        Page<PayWayDTO> page = payWayService.findAll(search, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }

    @GetMapping("/pay-ways/{id}")
    public ResponseEntity<PayWayDTO> get(@PathVariable Long id) {
        log.debug("REST request to get PayWay : {}", id);
        Optional<PayWayDTO> result = payWayService.findOneDto(id);
        return ResponseUtil.wrapOrNotFound(result);
    }

    @DeleteMapping("/pay-ways/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        log.debug("REST request to delete PayWay : {}", id);
        payWayService.delete(id);
        return ResponseEntity.noContent().headers(HeaderUtil.createEntityDeletionAlert(true, entityName, id.toString())).build();
    }
}
