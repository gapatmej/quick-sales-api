package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.DetailInvoiceClient;
import ec.com.newsolutions.service.DetailInvoiceService;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class DetailInvoiceServiceImpl implements DetailInvoiceService {


    @Override
    public DetailInvoiceClient save(DetailInvoiceClient detailInvoice) {
        return null;
    }

    @Override
    public Page<DetailInvoiceClient> findAll(Pageable pageable) {
        return null;
    }

    @Override
    public Optional<DetailInvoiceClient> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {

    }
}
