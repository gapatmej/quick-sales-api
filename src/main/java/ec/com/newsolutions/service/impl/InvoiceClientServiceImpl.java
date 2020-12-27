package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.repository.InvoiceClientRepository;
import ec.com.newsolutions.service.InvoiceClientService;
import ec.com.newsolutions.service.dto.InvoiceClientDTO;
import ec.com.newsolutions.service.mapper.InvoiceClientMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class InvoiceClientServiceImpl extends AbstractService implements InvoiceClientService {

    private InvoiceClientMapper invoiceClientMapper;
    private InvoiceClientRepository invoiceClientRepository;

    public InvoiceClientServiceImpl(InvoiceClientMapper invoiceClientMapper, InvoiceClientRepository invoiceClientRepository) {
        super(InvoiceClientServiceImpl.class);
        this.invoiceClientMapper = invoiceClientMapper;
        this.invoiceClientRepository = invoiceClientRepository;
    }

    @Override
    public InvoiceClientDTO save(InvoiceClientDTO invoiceClientDTO) {
        return null;
    }

    @Override
    public Page<InvoiceClientDTO> findAll(String search, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<InvoiceClientDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
