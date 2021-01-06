package ec.com.newsolutions.service.impl;

import ec.com.newsolutions.repository.specification.UtilsSpecification;
import ec.com.newsolutions.service.CategoryService;
import ec.com.newsolutions.domain.Category;
import ec.com.newsolutions.repository.CategoryRepository;
import ec.com.newsolutions.service.dto.CategoryDTO;
import ec.com.newsolutions.service.mapper.CategoryMapper;
import ec.com.newsolutions.utils.GsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class CategoryServiceImpl extends AbstractService implements CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryMapper categoryMapper;

    public CategoryServiceImpl(CategoryRepository categoryRepository, CategoryMapper categoryMapper) {
        super(CategoryServiceImpl.class);
        this.categoryRepository = categoryRepository;
        this.categoryMapper = categoryMapper;
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        log.debug("Request to save Category : {}", GsonUtils.entityToJson(categoryDTO));
        Category category =  save(categoryMapper.toEntity(categoryDTO));
        return categoryMapper.toDto(category);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<CategoryDTO> findAll(String search, Pageable pageable) {
        log.debug("Request to get all Categories");
        return categoryRepository.findAll(UtilsSpecification.<Category>getSpecificationWithWorkspace(search), pageable).map(categoryMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<CategoryDTO> findOneDto(Long id) {
        return findOne(id).map(categoryMapper::toDto);
    }

    @Override
    public Category save(Category category) {
        Category result =  categoryRepository.save(category);
        return result;
    }

    @Override
    public Optional<Category> findOne(Long id) {
        log.debug("Request to get Category : {}", id);
        return categoryRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Category : {}", id);
        categoryRepository.deleteById(id);
    }
}
