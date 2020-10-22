package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.Cellar;
import org.springframework.stereotype.Repository;

@Repository
public interface CellarRepository extends JpaRepositoryCustom<Cellar, Long> {

}
