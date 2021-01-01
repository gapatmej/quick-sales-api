package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.AddressCompany;
import ec.com.newsolutions.domain.Company;
import ec.com.newsolutions.domain.DetailInvoiceClient;
import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.domain.TaxDetailInvoice;
import ec.com.newsolutions.domain.enumeration.TaxTypeEnum;
import ec.com.newsolutions.repository.InvoiceClientRepository;
import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.service.AddressCompanyService;
import ec.com.newsolutions.service.CompanyService;
import ec.com.newsolutions.service.DetailInvoiceClientService;
import ec.com.newsolutions.service.ElectronicDocumentService;
import ec.com.newsolutions.service.InvoiceClientService;
import ec.com.newsolutions.service.dto.InvoiceClientDTO;
import ec.com.newsolutions.service.mapper.InvoiceClientMapper;
import ec.com.newsolutions.utils.GsonUtils;
import ec.com.newsolutions.web.rest.errors.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class InvoiceClientServiceImpl extends AbstractService implements InvoiceClientService {

    private final InvoiceClientMapper invoiceClientMapper;
    private final InvoiceClientRepository invoiceClientRepository;
    private final DetailInvoiceClientService detailInvoiceClientService;
    private final ElectronicDocumentService electronicDocumentService;
    private final AddressCompanyService addressCompanyService;
    private final CompanyService companyService;

    public InvoiceClientServiceImpl(InvoiceClientMapper invoiceClientMapper, InvoiceClientRepository invoiceClientRepository, DetailInvoiceClientService detailInvoiceClientService, ElectronicDocumentService electronicDocumentService, AddressCompanyService addressCompanyService, CompanyService companyService) {
        super(InvoiceClientServiceImpl.class);
        this.invoiceClientMapper = invoiceClientMapper;
        this.invoiceClientRepository = invoiceClientRepository;
        this.detailInvoiceClientService = detailInvoiceClientService;
        this.electronicDocumentService = electronicDocumentService;
        this.addressCompanyService = addressCompanyService;
        this.companyService = companyService;
    }

    @Override
    public InvoiceClientDTO save(InvoiceClientDTO invoiceClientDTO) {
        log.debug("Request to save InvoiceClient : {}", GsonUtils.entityToJson(invoiceClientDTO));
        Company company = companyService.findOne(invoiceClientDTO.getCompanyId())
            .orElseThrow(()-> new EntityNotFoundException(invoiceClientDTO.getCompanyId()));

        AddressCompany addressCompany = company.getAddressCompanies().stream()
            .filter(aC->aC.getId().equals(invoiceClientDTO.getAddressCompanyId()))
            .findFirst().orElseThrow(()-> new EntityNotFoundException(invoiceClientDTO.getAddressCompanyId()));

        InvoiceClientDTO result;
        InvoiceClient invoiceClient = invoiceClientMapper.toEntity(invoiceClientDTO);
        electronicDocumentService.build(invoiceClient);
        invoiceClient.setBusinessName(company.getBusinessName());
        invoiceClient.setIdentificationType(company.getIdentificationType());
        invoiceClient.setIdentification(company.getIdentification());
        invoiceClient.setAddress(addressCompany.getAddress());
        invoiceClient.setPhone(addressCompany.getPhone());
        invoiceClient.setEmail(company.getEmail());

        detailInvoiceClientService.build(invoiceClient);
        calculateTotals(invoiceClient);
        invoiceClientRepository.save(invoiceClient);

        detailInvoiceClientService.saveAll(invoiceClient.getDetailsInvoiceClient());

        result = invoiceClientMapper.toDto(invoiceClient);

        return result;
    }

    @Override
    @Transactional(readOnly = true)
    public Page<InvoiceClientDTO> findAll(String search, Pageable pageable) {
        log.debug("Request to get all InvoiceClient");
        return invoiceClientRepository.findAll( UtilsSpecification.<InvoiceClient>getSpecificationWithWorkspace(search), pageable).map(invoiceClientMapper::toDtoLight);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<InvoiceClientDTO> findOneDto(Long id) {
        return findOne(id).map(invoiceClientMapper::toDto);
    }

    @Override
    public Optional<InvoiceClient> findOne(Long id) {
        log.debug("Request to get InvoiceClient : {}", id);
        return invoiceClientRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete InvoiceClient : {}", id);
        detailInvoiceClientService.deleteByInvoiceClient(id);
        invoiceClientRepository.deleteById(id);
    }

    @Override
    public void calculateTotals(InvoiceClient invoiceClient) {
        List<DetailInvoiceClient> detailsInvoiceClient = invoiceClient.getDetailsInvoiceClient().stream()
            .filter(dIC->dIC.getDeleted() == false).collect(Collectors.toList());
        BigDecimal totalDiscount = BigDecimal.ZERO;
        BigDecimal totalBaseTaxIVA = BigDecimal.ZERO;
        BigDecimal totalBaseTaxICE = BigDecimal.ZERO;
        BigDecimal totalTaxIVA = BigDecimal.ZERO;
        BigDecimal totalTaxICE = BigDecimal.ZERO;
        BigDecimal totalWithoutTax;
        BigDecimal total;

        for (DetailInvoiceClient detailInvoiceClient : detailsInvoiceClient) {
            for (TaxDetailInvoice taxDetailInvoice : detailInvoiceClient.getTaxesDetailInvoice()){
                if(taxDetailInvoice.getTax().getTaxType().equals(TaxTypeEnum.IVA)){
                    totalBaseTaxIVA = totalBaseTaxIVA.add(taxDetailInvoice.getTaxBase());
                    totalTaxIVA = totalTaxIVA.add(taxDetailInvoice.getAmount());
                }else if(taxDetailInvoice.getTax().equals(TaxTypeEnum.ICE)){
                    totalBaseTaxICE = totalBaseTaxICE.add(taxDetailInvoice.getTaxBase());
                    totalTaxICE = totalTaxICE.add(taxDetailInvoice.getAmount());
                }
            }
            totalDiscount = totalDiscount.add(detailInvoiceClient.getDiscount());
        }
        totalWithoutTax = totalBaseTaxIVA.add(totalBaseTaxICE);
        total= totalWithoutTax.add(totalTaxIVA).add(totalBaseTaxICE);

        invoiceClient.setTotalWithoutTax(totalWithoutTax);
        invoiceClient.setTotalDiscount(totalDiscount);
        invoiceClient.setTotalBaseTaxIVA(totalBaseTaxIVA);
        invoiceClient.setTotalBaseTaxICE(totalBaseTaxICE);
        invoiceClient.setTotalTaxIVA(totalTaxIVA);
        invoiceClient.setTotalTaxICE(totalTaxICE);
        invoiceClient.setTotal(total);
    }
}
