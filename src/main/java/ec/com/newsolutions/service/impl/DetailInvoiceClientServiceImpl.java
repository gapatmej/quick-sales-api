package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.DetailInvoiceClient;
import ec.com.newsolutions.repository.DetailInvoiceClientRepository;
import ec.com.newsolutions.service.DetailInvoiceClientService;
import ec.com.newsolutions.service.dto.DetailInvoiceClientDTO;
import ec.com.newsolutions.service.mapper.DetailInvoiceClientMapper;
import net.logstash.logback.encoder.org.apache.commons.lang3.BooleanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class DetailInvoiceClientServiceImpl extends AbstractService implements DetailInvoiceClientService {

    private final DetailInvoiceClientMapper detailInvoiceClientMapper;
    private final DetailInvoiceClientRepository detailInvoiceClientRepository;

    public DetailInvoiceClientServiceImpl(DetailInvoiceClientMapper detailInvoiceClientMapper, DetailInvoiceClientRepository detailInvoiceClientRepository) {
        super(DetailInvoiceClientServiceImpl.class);
        this.detailInvoiceClientMapper = detailInvoiceClientMapper;
        this.detailInvoiceClientRepository = detailInvoiceClientRepository;
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
}
