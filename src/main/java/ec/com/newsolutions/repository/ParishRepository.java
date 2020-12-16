package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.Parish;
import org.springframework.stereotype.Repository;

@Repository
public interface ParishRepository extends JpaRepositoryCustom<Parish,Long> {
}
