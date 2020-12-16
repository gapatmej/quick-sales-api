package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.Canton;
import org.springframework.stereotype.Repository;

@Repository
public interface CantonRepository extends JpaRepositoryCustom<Canton, Long>  {
}
