package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.PayWay;
import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.service.PayWayService;
import ec.com.newsolutions.repository.PayWayRepository;
import ec.com.newsolutions.service.dto.PayWayDTO;
import ec.com.newsolutions.service.mapper.PayWayMapper;
import ec.com.newsolutions.utils.GsonUtils;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@Transactional
public class PayWayServiceImpl extends AbstractService implements PayWayService {
    private final PayWayRepository payWayRepository;
    private final PayWayMapper payWayMapper;

    public PayWayServiceImpl(PayWayRepository payWayRepository, PayWayMapper payWayMapper) {
        super(PayWayServiceImpl.class);
        this.payWayRepository = payWayRepository;
        this.payWayMapper = payWayMapper;
    }

    @Override
    public PayWayDTO save(PayWayDTO payWayDTO) {
        log.debug("Request to save PayWay : {}", GsonUtils.entityToJson(payWayDTO));
        PayWay result = save(payWayMapper.toEntity(payWayDTO));
        return payWayMapper.toDto(result);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<PayWayDTO> findAll(String search, Pageable pageable) {
        return payWayRepository.findAll(UtilsSpecification.<PayWay>getSpecificationWithWorkspace(search), pageable).map(payWayMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<PayWayDTO> findOneDto(Long id) {
        return findOne(id).map(payWayMapper::toDto);
    }

    @Override
    public PayWay save(PayWay payWay) {
        PayWay result = payWayRepository.save(payWay);
        return result;
    }

    @Override
    public Optional<PayWay> findOne(Long id) {
        log.debug("Request to get PayWay : {}", id);
        return payWayRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete PayWay : {}", id);
        payWayRepository.deleteById(id);
    }
}
