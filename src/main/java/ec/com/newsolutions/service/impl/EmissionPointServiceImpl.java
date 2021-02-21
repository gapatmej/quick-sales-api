package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.EmissionPoint;
import ec.com.newsolutions.repository.EmissionPointRepository;
import ec.com.newsolutions.service.EmissionPointService;
import ec.com.newsolutions.service.dto.BranchOfficeDTO;
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
import java.util.stream.Collectors;

@Service
@Transactional
public class EmissionPointServiceImpl extends AbstractService implements EmissionPointService {

    private final EmissionPointMapper emissionPointMapper;
    private final EmissionPointRepository emissionPointRepository;

    public EmissionPointServiceImpl(EmissionPointMapper emissionPointMapper, EmissionPointRepository emissionPointRepository) {
        super(EmissionPointServiceImpl.class);
        this.emissionPointMapper = emissionPointMapper;
        this.emissionPointRepository = emissionPointRepository;
    }

    @Override
    public EmissionPointDTO save(EmissionPointDTO emissionPointDTO) {
        log.debug("Request to save Emission Point : {}", emissionPointDTO);
        EmissionPoint emissionPoint = save(emissionPointMapper.toEntity(emissionPointDTO));
        return emissionPointMapper.toDto(emissionPoint);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<EmissionPointDTO> findAll(String search, Pageable pageable) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<EmissionPointDTO> findOneDto(Long id) {
        return findOne(id).map(emissionPointMapper::toDto);
    }

    @Override
    public EmissionPoint save(EmissionPoint emissionPoint) {
        EmissionPoint result = emissionPointRepository.save(emissionPoint);
        return result;
    }

    @Override
    public Optional<EmissionPoint> findOne(Long id) {
        log.debug("Request to get Emission Point  : {}", id);
        return emissionPointRepository.findById(id);
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

    @Override
    public List<EmissionPointDTO> updateByBranchOffice(BranchOfficeDTO branchOfficeDTO) {
        log.debug("Request to save Emission Points : {}", branchOfficeDTO.getEmissionPoints());

        List<EmissionPoint> emissionPoints = emissionPointRepository.findByBranchOfficeId(branchOfficeDTO.getId());

        List<Long> newsId = branchOfficeDTO.getEmissionPoints().stream().filter(eP->eP.getId()!= null).map(EmissionPointDTO::getId).collect(Collectors.toList());
        List<Long> oldsIdToDeleted = emissionPoints.stream().map(EmissionPoint::getId).collect(Collectors.toList());
        oldsIdToDeleted.removeAll(newsId);

        emissionPointRepository.deleteInBatch(emissionPoints.stream().filter(eP->oldsIdToDeleted.contains(eP.getId())).collect(Collectors.toList()));

        List<EmissionPointDTO> result = new ArrayList<>();
        branchOfficeDTO.getEmissionPoints().forEach(ep->{
            result.add(save(ep));
        });

        return result;

    }
}
