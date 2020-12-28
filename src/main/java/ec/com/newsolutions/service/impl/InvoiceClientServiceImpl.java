package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.repository.InvoiceClientRepository;
import ec.com.newsolutions.repository.specification.UtilsSpecification;
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

    public InvoiceClientServiceImpl(InvoiceClientMapper invoiceClientMapper, InvoiceClientRepository invoiceClientRepository, DetailInvoiceClientService detailInvoiceClientService, ElectronicDocumentService electronicDocumentService) {
        super(InvoiceClientServiceImpl.class);
        this.invoiceClientMapper = invoiceClientMapper;
        this.invoiceClientRepository = invoiceClientRepository;
        this.detailInvoiceClientService = detailInvoiceClientService;
        this.electronicDocumentService = electronicDocumentService;
    }

    @Override
    public InvoiceClientDTO save(InvoiceClientDTO invoiceClientDTO) {
        InvoiceClientDTO result ;
        log.debug("Request to save InvoiceClient : {}", GsonUtils.entityToJson(invoiceClientDTO));
        InvoiceClient invoiceClient = invoiceClientMapper.toEntity(invoiceClientDTO);
        electronicDocumentService.build(invoiceClient);

        //final InvoiceClient invoiceClient = invoiceClientRepository.save(invoiceClientMapper.toEntity(invoiceClientDTO));
        result = invoiceClientMapper.toDto(invoiceClient);

       /* invoiceClientDTO.getDetailsInvoiceClient().forEach(iC -> iC.setInvoiceClientId(invoiceClient.getId()));
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
    public Optional<InvoiceClientDTO> findOne(Long id) {
        log.debug("Request to get InvoiceClient : {}", id);
        return invoiceClientRepository.findById(id).map(invoiceClientMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete InvoiceClient : {}", id);
        detailInvoiceClientService.deleteByInvoiceClient(id);
        invoiceClientRepository.deleteById(id);
    }
}
