package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.EmissionPointUser;
import ec.com.newsolutions.repository.EmissionPointUserRepository;
import ec.com.newsolutions.service.EmissionPointUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EmissionPointUserServiceImpl implements EmissionPointUserService {

    private final Logger log = LoggerFactory.getLogger(EmissionPointUserServiceImpl.class);

    private final EmissionPointUserRepository emissionPointUserRepository;

    public EmissionPointUserServiceImpl(EmissionPointUserRepository emissionPointUserRepository) {
        this.emissionPointUserRepository = emissionPointUserRepository;
    }

    @Override
    public List<EmissionPointUser> findAllByUser(String login) {
        return emissionPointUserRepository.findEmissionPointUserByUser_Login(login) ;
    }
}
