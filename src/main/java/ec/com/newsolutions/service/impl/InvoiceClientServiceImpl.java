package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.service.ElectronicDocumentService;
import ec.com.newsolutions.service.InvoiceClientService;
import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.repository.InvoiceClientRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Service Implementation for managing {@link InvoiceClient}.
 */
@Service
@Transactional
public class InvoiceClientServiceImpl implements InvoiceClientService {

    private final Logger log = LoggerFactory.getLogger(InvoiceClientServiceImpl.class);

    private final InvoiceClientRepository invoiceClientRepository;
    private final ElectronicDocumentService documentElectronicService;

    public InvoiceClientServiceImpl(InvoiceClientRepository invoiceClientRepository,
                                    ElectronicDocumentService documentElectronicService) {
        this.invoiceClientRepository = invoiceClientRepository;
        this.documentElectronicService = documentElectronicService;
    }

    /**
     * Save a invoiceClient.
     *
     * @param invoiceClient the entity to save.
     * @return the persisted entity.
     */
    @Override
    public InvoiceClient save(InvoiceClient invoiceClient) {
        log.debug("Request to save InvoiceClient : {}", invoiceClient);

        /*Pruebas


        invoiceClient.setSriEnviroment(SRIEnviromentEnum.PRODUCTION);
        invoiceClient.setAccessKey("101606046406046490697979797461986456");
        invoiceClient.setReceiptType(ReceiptTypeEnum.INVOICE);


        invoiceClient.getDetailInvoices().stream().forEach(d->d.setInvoice(invoiceClient));


        return invoiceClientRepository.save(invoiceClient);
        invoiceClient.setId(new Long(1));*/
      this.documentElectronicService.generateXML(invoiceClient);

      return invoiceClient;

    }

    /**
     * Get all the invoiceClients.
     *
     * @param pageable the pagination information.
     * @return the list of entities.
     */
    @Override
    @Transactional(readOnly = true)
    public Page<InvoiceClient> findAll(Pageable pageable) {
        log.debug("Request to get all InvoiceClients");
        return invoiceClientRepository.findAll(pageable);
    }

    /**
     * Get one invoiceClient by id.
     *
     * @param id the id of the entity.
     * @return the entity.
     */
    @Override
    @Transactional(readOnly = true)
    public Optional<InvoiceClient> findOne(Long id) {
        log.debug("Request to get InvoiceClient : {}", id);
        return invoiceClientRepository.findById(id);
    }

    /**
     * Delete the invoiceClient by id.
     *
     * @param id the id of the entity.
     */
    @Override
    public void delete(Long id) {
        log.debug("Request to delete InvoiceClient : {}", id);
        invoiceClientRepository.deleteById(id);
    }
}
