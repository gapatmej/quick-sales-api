package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.Company;
import ec.com.newsolutions.domain.InvoiceClient;
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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class InvoiceClientServiceImpl extends AbstractService implements InvoiceClientService {

    private final InvoiceClientMapper invoiceClientMapper;
    private final InvoiceClientRepository invoiceClientRepository;
    private final DetailInvoiceClientService detailInvoiceClientService;
    private final ElectronicDocumentService electronicDocumentService;
    private final AddressCompanyService addressCompanyService;
    private final CompanyService companyService;
   //private final CompanyService companyService;

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
       /* Company company = companyService.findOne(invoiceClientDTO.getCompanyId())
            .map(com::toEntity).orElseThrow(()-> new EntityNotFoundException(electronicDocument.getOrganization().getId()));*/

        InvoiceClientDTO result;
        InvoiceClient invoiceClient = invoiceClientMapper.toEntity(invoiceClientDTO);
        electronicDocumentService.build(invoiceClient);


        invoiceClient = invoiceClientRepository.save(invoiceClient);
        result = invoiceClientMapper.toDto(invoiceClient);

     /*   invoiceClientDTO.getDetailsInvoiceClient().forEach(iC -> iC.setInvoiceClientId(result.getId()));
        result.setDetailsInvoiceClient(detailInvoiceClientService.saveAll(invoiceClientDTO.getDetailsInvoiceClient()));*/

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
}
