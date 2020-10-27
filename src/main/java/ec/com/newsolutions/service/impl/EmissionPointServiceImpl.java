package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.EmissionPoint;
import ec.com.newsolutions.repository.EmissionPointRepository;
import ec.com.newsolutions.service.EmissionPointService;
import ec.com.newsolutions.service.dto.EmissionPointDTO;
import ec.com.newsolutions.service.mapper.EmissionPointMapper;
import org.apache.commons.lang3.BooleanUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EmissionPointServiceImpl implements EmissionPointService {

    private final EmissionPointMapper emissionPointMapper;
    private final EmissionPointRepository emissionPointRepository;
    private final Logger log = LoggerFactory.getLogger(EmissionPointServiceImpl.class);

    public EmissionPointServiceImpl(EmissionPointMapper emissionPointMapper, EmissionPointRepository emissionPointRepository) {
        this.emissionPointMapper = emissionPointMapper;
        this.emissionPointRepository = emissionPointRepository;
    }

    @Override
    public EmissionPointDTO save(EmissionPointDTO emissionPointDTO) {
        log.debug("Request to save Emission Point : {}", emissionPointDTO);
        EmissionPoint emissionPoint = emissionPointRepository.save(emissionPointMapper.toEntity(emissionPointDTO));
        return emissionPointMapper.toDto(emissionPoint);
    }

    @Override
    public List<EmissionPointDTO> saveAll(List<EmissionPointDTO> emissionPointDTOS) {
        log.debug("Request to save Emission Points : {}", emissionPointDTOS);
        List<EmissionPointDTO> emissionPointDTO = new ArrayList<>();
        emissionPointDTOS.forEach(ep->{
            if(BooleanUtils.isTrue(ep.getDeleted())){
                delete(ep.getId());
            }else{
                emissionPointDTO.add(save(ep));
            }
        });

        return emissionPointDTO;
    }

    @Override
    public Page<EmissionPointDTO> findAll(String search, Pageable pageable) {
        return null;
    }

    @Override
    public Optional<EmissionPointDTO> findOne(Long id) {
        return Optional.empty();
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Emission Point : {}", id);
        emissionPointRepository.deleteById(id);
    }

    @Override
    public void deleteByBranchOffice(Long idBranchOffice) {
        emissionPointRepository.deleteByBranchOfficeId(idBranchOffice);
    }
}
