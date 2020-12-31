package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.DetailInvoiceClient;
import ec.com.newsolutions.domain.Product;
import ec.com.newsolutions.domain.Tax;
import ec.com.newsolutions.domain.TaxDetailInvoice;
import ec.com.newsolutions.repository.DetailInvoiceClientRepository;
import ec.com.newsolutions.service.DetailInvoiceClientService;
import ec.com.newsolutions.service.ProductService;
import ec.com.newsolutions.service.TaxService;
import ec.com.newsolutions.service.dto.DetailInvoiceClientDTO;
import ec.com.newsolutions.service.mapper.DetailInvoiceClientMapper;
import ec.com.newsolutions.web.rest.errors.EntityNotFoundException;
import net.logstash.logback.encoder.org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

    public DetailInvoiceClientServiceImpl(DetailInvoiceClientMapper detailInvoiceClientMapper, DetailInvoiceClientRepository detailInvoiceClientRepository, ProductService productService, TaxService taxService) {
        super(DetailInvoiceClientServiceImpl.class);
        this.detailInvoiceClientMapper = detailInvoiceClientMapper;
        this.detailInvoiceClientRepository = detailInvoiceClientRepository;
        this.productService = productService;
        this.taxService = taxService;
    }


    @Override
    public List<DetailInvoiceClientDTO> saveAll(List<DetailInvoiceClientDTO> detailInvoiceClientDTOS) {
        log.debug("Request to save Detail Invoice Client : {}", detailInvoiceClientDTOS);
        List<DetailInvoiceClientDTO> result = new ArrayList<>();
        detailInvoiceClientDTOS.forEach(ep->{
            if(BooleanUtils.isTrue(ep.isDeleted())){
                delete(ep.getId());
            }else{
                result.add(save(ep));
            }
        });

        return result;
    }

    @Override
    public DetailInvoiceClientDTO save(DetailInvoiceClientDTO invoiceClientDTO) {
        log.debug("Request to save Detail Invoce : {}", invoiceClientDTO);
        DetailInvoiceClient detailInvoiceClient = detailInvoiceClientRepository.save(detailInvoiceClientMapper.toEntity(invoiceClientDTO));
        return detailInvoiceClientMapper.toDto(detailInvoiceClient);
    }

    @Override
    public Page<DetailInvoiceClientDTO> findAll(String search, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Optional<DetailInvoiceClientDTO> findOneDto(Long id) {
        return findOne(id).map(detailInvoiceClientMapper::toDto);
    }

    @Override
    public Optional<DetailInvoiceClient> findOne(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Detail Invoice Client : {}", id);
        detailInvoiceClientRepository.deleteById(id);
    }

    @Override
    public void deleteByInvoiceClient(Long idInvoiceClient) {
        detailInvoiceClientRepository.deleteByInvoiceClient(idInvoiceClient);
    }

    @Override
    public void build(Set<DetailInvoiceClient> detailsInvoiceClient) {
        List<Product> products = productService.findByIdIn(detailsInvoiceClient.stream()
            .map(dIC->dIC.getProduct().getId()).collect(Collectors.toList()));
        List<Tax> taxes = taxService.findByIdIn(products.stream()
            .flatMap(p-> Stream.of(p.getIva().getId(),p.getIce()!=null?p.getIce().getId():null)).collect(Collectors.toList()));

        for (DetailInvoiceClient detailInvoiceClient :detailsInvoiceClient) {
            Long productId = detailInvoiceClient.getProduct().getId();
            Product product = products.stream().filter(p -> p.getId().equals(productId))
                .findFirst().orElseThrow(()->new EntityNotFoundException(productId));
            BigDecimal total = detailInvoiceClient.getQuantity()
            .multiply(
                detailInvoiceClient.getUnitPrice().subtract(detailInvoiceClient.getDiscount())
            );

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
}
