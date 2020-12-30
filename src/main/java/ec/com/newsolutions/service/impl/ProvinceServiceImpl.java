package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.domain.Province;
import ec.com.newsolutions.repository.ProvinceRepository;
import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.service.ProvinceService;
import ec.com.newsolutions.service.dto.ProvinceDTO;
import ec.com.newsolutions.service.mapper.ProvinceMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class ProvinceServiceImpl extends AbstractService implements ProvinceService {

    private final ProvinceMapper provinceMapper;
    private final ProvinceRepository provinceRepository;

    public ProvinceServiceImpl(ProvinceMapper provinceMapper, ProvinceRepository provinceRepository) {
        super(ProvinceServiceImpl.class);

        this.provinceMapper = provinceMapper;
        this.provinceRepository = provinceRepository;
    }

    @Override
    public ProvinceDTO save(ProvinceDTO invoiceClientDTO) {
        throw new UnsupportedOperationException();
    }

    @Override
    @Transactional(readOnly = true)
    public Page<ProvinceDTO> findAll(String search, Pageable pageable) {
        log.debug("Request to get all Provinces");
        return provinceRepository.findAll( UtilsSpecification.<Province>getSpecificationWithWorkspace(search), pageable).map(provinceMapper::toDto);
    }

    @Override
    public Optional<ProvinceDTO> findOneDto(Long id) {
        return findOne(id).map(provinceMapper::toDto);
    }

    @Override
    public Optional<Province> findOne(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException();
    }
}
