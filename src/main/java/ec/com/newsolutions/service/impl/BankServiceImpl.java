package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.Bank;
import ec.com.newsolutions.repository.BankRepository;
import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.service.BankService;
import ec.com.newsolutions.service.dto.BankDTO;
import ec.com.newsolutions.service.mapper.BankMapper;
import ec.com.newsolutions.utils.GsonUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class BankServiceImpl extends AbstractService implements BankService {

    private final BankRepository bankRepository;
    private final BankMapper bankMapper;

    public BankServiceImpl(BankRepository bankRepository, BankMapper bankMapper) {
        super(BankServiceImpl.class);
        this.bankRepository = bankRepository;
        this.bankMapper = bankMapper;
    }

    @Override
    public BankDTO save(BankDTO bankDTO) {
        log.debug("Request to save Banck : {}", GsonUtils.entityToJson(bankDTO));
        Bank bank =  bankRepository.save(bankMapper.toEntity(bankDTO));
        return bankMapper.toDto(bank);
    }

    @Override
    public Page<BankDTO> findAll(String search, Pageable pageable) {
        return bankRepository.findAll( UtilsSpecification.<Bank>getSpecificationWithWorkspace(search), pageable).map(bankMapper::toDto);
    }

    @Override
    public Optional<BankDTO> findOneDto(Long id) {
        return findOne(id).map(bankMapper::toDto);
    }

    @Override
    public Optional<Bank> findOne(Long id) {
        log.debug("Request to get Bank : {}", id);
        return bankRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Bank : {}", id);
        bankRepository.deleteById(id);
    }
}
