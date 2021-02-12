package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.SriMessage;
import ec.com.newsolutions.repository.SriMessageRepository;
import ec.com.newsolutions.service.SriMessageService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class SriMessageServiceImpl extends AbstractService implements SriMessageService {

    private final SriMessageRepository sriMessageRepository;

    public SriMessageServiceImpl(SriMessageRepository sriMessageRepository) {
        super(SriMessageServiceImpl.class);
        this.sriMessageRepository = sriMessageRepository;
    }

    @Override
    public SriMessage save(SriMessage sriMessage) {
        return sriMessageRepository.save(sriMessage);
    }

    @Override
    public Optional<SriMessage> findOne(Long id) {
        throw new UnsupportedOperationException();
    }
}
