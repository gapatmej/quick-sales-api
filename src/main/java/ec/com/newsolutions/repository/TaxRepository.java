package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.Tax;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaxRepository extends JpaRepositoryCustom<Tax, Long> {
    List<Tax> findByIdIn(List<Long> ids);
}
