package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Category;
import ec.com.newsolutions.service.dto.CategoryDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category>  {
}
