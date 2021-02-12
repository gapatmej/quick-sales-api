package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.DetailInvoiceClient;
import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.domain.Product;
import ec.com.newsolutions.domain.Tax;
import ec.com.newsolutions.domain.TaxDetailInvoice;
import ec.com.newsolutions.repository.DetailInvoiceClientRepository;
import ec.com.newsolutions.service.DetailInvoiceClientService;
import ec.com.newsolutions.service.ProductService;
import ec.com.newsolutions.service.TaxDetailInvoiceService;
import ec.com.newsolutions.service.TaxService;
import ec.com.newsolutions.service.mapper.DetailInvoiceClientMapper;
import ec.com.newsolutions.web.rest.errors.EntityNotFoundException;
import net.logstash.logback.encoder.org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;


@Service
@Transactional
public class DetailInvoiceClientServiceImpl extends AbstractService implements DetailInvoiceClientService {

    private final DetailInvoiceClientMapper detailInvoiceClientMapper;
    private final DetailInvoiceClientRepository detailInvoiceClientRepository;
    private final ProductService productService;
    private final TaxService taxService;
    private final TaxDetailInvoiceService taxDetailInvoiceService;

    public DetailInvoiceClientServiceImpl(DetailInvoiceClientMapper detailInvoiceClientMapper, DetailInvoiceClientRepository detailInvoiceClientRepository, ProductService productService, TaxService taxService, TaxDetailInvoiceService taxDetailInvoiceService) {
        super(DetailInvoiceClientServiceImpl.class);
        this.detailInvoiceClientMapper = detailInvoiceClientMapper;
        this.detailInvoiceClientRepository = detailInvoiceClientRepository;
        this.productService = productService;
        this.taxService = taxService;
        this.taxDetailInvoiceService = taxDetailInvoiceService;
    }

    @Override
    public void saveAll(Set<DetailInvoiceClient> detailsInvoiceClient) {
        List<Long> detailInvoiceClientIds = new ArrayList<>();
        detailsInvoiceClient.forEach(ep->{
            if(BooleanUtils.isTrue(ep.getDeleted())){
                detailInvoiceClientIds.add(ep.getId());
                detailsInvoiceClient.remove(ep);
            }else{
                save(ep);
            }
        });
        deleteByIdIn(detailInvoiceClientIds);
    }


    @Override
    public DetailInvoiceClient save (DetailInvoiceClient detailInvoiceClient){
        DetailInvoiceClient result =  detailInvoiceClientRepository.save(detailInvoiceClient);
        taxDetailInvoiceService.saveAll(detailInvoiceClient.getTaxesDetailInvoice());
        return result;
    }

    @Override
    public void deleteByInvoiceClient(Long idInvoiceClient) {
        taxDetailInvoiceService.deleteByInvoiceClientId(idInvoiceClient);
        detailInvoiceClientRepository.deleteByInvoiceClientId(idInvoiceClient);
    }

    @Override
    public void deleteByIdIn(List<Long> ids) {
        taxDetailInvoiceService.deleteByDetailInvoiceClientIdIn(ids);
        detailInvoiceClientRepository.deleteByIdIn(ids);
    }

    @Override
    public void build(InvoiceClient invoiceClient) {

        List<DetailInvoiceClient> detailsInvoiceClient = invoiceClient.getDetailsInvoiceClient().stream().filter(dIC->dIC.getDeleted() == false).collect(Collectors.toList());

        List<Product> products = productService.findByIdIn(detailsInvoiceClient.stream()
            .map(dIC->dIC.getProduct().getId()).collect(Collectors.toList()));
        List<Tax> taxes = taxService.findByIdIn(products.stream()
            .flatMap(p-> Stream.of(p.getIva().getId(),p.getIce()!=null?p.getIce().getId():null)).collect(Collectors.toList()));

        for (DetailInvoiceClient detailInvoiceClient :invoiceClient.getDetailsInvoiceClient()) {
            if(detailInvoiceClient.getDeleted()){
                continue;
            }

            Long productId = detailInvoiceClient.getProduct().getId();
            Product product = products.stream().filter(p -> p.getId().equals(productId))
                .findFirst().orElseThrow(()->new EntityNotFoundException(productId));
            BigDecimal total = detailInvoiceClient.getQuantity()
            .multiply(
                detailInvoiceClient.getUnitPrice().subtract(detailInvoiceClient.getDiscount())
            );

            detailInvoiceClient.setInvoiceClient(invoiceClient);
            detailInvoiceClient.setMainCode(product.getMainCode());
            detailInvoiceClient.setAuxiliaryCode(product.getAuxiliaryCode());
            detailInvoiceClient.setDescription(product.getName());
            detailInvoiceClient.setTotal(total);

            Set<TaxDetailInvoice> taxesDetailInvoice = new HashSet();
            Long taxIvaId = product.getIva().getId();
            Tax tax = taxes.stream().filter(t->t.getId().equals(taxIvaId)).findFirst().orElseThrow(()->new EntityNotFoundException(taxIvaId));
            taxesDetailInvoice.add(new TaxDetailInvoice(tax,detailInvoiceClient));

            if(product.getIce() != null){
                Long taxIceId = product.getIce().getId();
                tax = taxes.stream().filter(t->t.getId().equals(taxIceId)).findFirst().orElseThrow(()->new EntityNotFoundException(taxIceId));
                taxesDetailInvoice.add(new TaxDetailInvoice(tax,detailInvoiceClient));
            }
            detailInvoiceClient.setTaxesDetailInvoice(taxesDetailInvoice);
        }

    }

    @Override
    public Optional<DetailInvoiceClient> findOne(Long id) {
        throw new UnsupportedOperationException();
    }
}
