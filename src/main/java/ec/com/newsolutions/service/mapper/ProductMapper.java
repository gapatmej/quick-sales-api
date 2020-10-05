package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Product;
import ec.com.newsolutions.service.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProductMapper extends EntityMapper<ProductDTO, Product> {

    @Mapping(source = "iva.id", target = "ivaId")
    @Mapping(source = "ice.id", target = "iceId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "cellar.id", target = "cellarId")
    @Mapping(source = "unit.id", target = "unitId")
    ProductDTO toDto(Product product);

    @Mapping(source = "ivaId", target = "iva.id")
    @Mapping(source = "iceId", target = "ice.id")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "cellarId", target = "cellar.id")
    @Mapping(source = "unitId", target = "unit.id")
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdDate", ignore = true)
    @Mapping(target = "lastModifiedBy", ignore = true)
    @Mapping(target = "lastModifiedDate", ignore = true)
    Product toEntity(ProductDTO productDTO);
}
