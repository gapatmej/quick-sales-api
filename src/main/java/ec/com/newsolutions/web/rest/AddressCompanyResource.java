package ec.com.newsolutions.web.rest;

import ec.com.newsolutions.service.AddressCompanyService;
import ec.com.newsolutions.service.dto.AddressCompanyDTO;
import ec.com.newsolutions.service.dto.BranchOfficeDTO;
import io.github.jhipster.web.util.PaginationUtil;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.util.List;

@RestController
@RequestMapping("/api")
public class AddressCompanyResource extends AbstractResource{

    private final AddressCompanyService addressCompanyService;

    public AddressCompanyResource(AddressCompanyService addressCompanyService) {
        super(AddressCompanyResource.class,"addressCompany");
        this.addressCompanyService = addressCompanyService;
    }

    @GetMapping("/addresses-company")
    public ResponseEntity<List<AddressCompanyDTO>> getAll(String search, Pageable pageable) {
        log.debug("REST request to get a page of AddressesCompany");
        Page<AddressCompanyDTO> page = addressCompanyService.findAll(search, pageable);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(ServletUriComponentsBuilder.fromCurrentRequest(), page);
        return ResponseEntity.ok().headers(headers).body(page.getContent());
    }
}
