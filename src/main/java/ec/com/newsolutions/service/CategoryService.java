package ec.com.newsolutions.service;

import ec.com.newsolutions.service.dto.CategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface CategoryService extends AbstractService<CategoryDTO> {

    CategoryDTO save(CategoryDTO categoryDTO);

    Page<CategoryDTO> findAll(String search, Pageable pageable);

    Optional<CategoryDTO> findOne(Long id);

    void delete(Long id);
}
