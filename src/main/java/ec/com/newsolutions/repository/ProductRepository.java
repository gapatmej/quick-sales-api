package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.Product;

import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepositoryCustom<Product, Long> {

    List<Product> findByIdIn(List<Long> ids);
}
