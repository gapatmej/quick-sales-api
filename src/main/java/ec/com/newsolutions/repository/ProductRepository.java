package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.Product;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Product entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = "select distinct p from Product p where lower(p.mainCode) like lower(concat('%', :query,'%')) or " +
        " lower(p.auxiliaryCode) like lower(concat('%', :query,'%')) or lower(p.name) like lower(concat('%', :query,'%'))",
        countQuery = "select count(distinct p) from Product p where (lower(p.mainCode) like :query) or (lower(p.auxiliaryCode) like :query) or (lower(p.name) like :query)")
    Page<Product> search(Pageable page, @Param("query") String query);

    @Query("select p from Product p left join fetch p.iva left join fetch p.ice "+
        " where p.id = :id ")
    Optional<Product> findById2(@Param("id") Long id);

}
