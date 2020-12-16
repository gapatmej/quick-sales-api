package ec.com.newsolutions.service.mapper;

import ec.com.newsolutions.domain.Product;
import ec.com.newsolutions.service.dto.ProductDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(config = EntityMapperConfigIgnoreAuditProps.class, uses = {TaxMapper.class})
public interface ProductMapper {

    @Mapping(target = "organizationId", source = "organization.id")
    @Mapping(source = "iva.id", target = "ivaId")
    @Mapping(source = "ice.id", target = "iceId")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(source = "cellar.id", target = "cellarId")
    @Mapping(source = "unit.id", target = "unitId")
    ProductDTO toDto(Product product);

    @Mapping(source = "organizationId", target = "organization.id")
    @Mapping(source = "ivaId", target = "iva")
    @Mapping(source = "iceId", target = "ice")
    @Mapping(source = "categoryId", target = "category.id")
    @Mapping(source = "cellarId", target = "cellar.id")
    @Mapping(source = "unitId", target = "unit.id")
    Product toEntity(ProductDTO productDTO);

}
