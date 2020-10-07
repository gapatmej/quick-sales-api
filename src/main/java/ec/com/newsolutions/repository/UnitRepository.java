package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.Unit;
import org.springframework.stereotype.Repository;

@Repository
public interface UnitRepository extends JpaRepositoryCustom<Unit,Long>{
}
