package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.Company;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Company entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    @Query(value = "select distinct c from Company c where lower(c.businessName) like lower(concat('%', :query,'%')) or " +
        " lower(c.identification) like lower(concat('%', :query,'%')) ",
        countQuery = "select count(distinct c) from Company c where lower(c.businessName) like lower(concat('%', :query,'%')) or " +
            "lower(c.identification) like lower(concat('%', :query,'%')) ")
    Page<Company> search(Pageable page, @Param("query") String query);

}
