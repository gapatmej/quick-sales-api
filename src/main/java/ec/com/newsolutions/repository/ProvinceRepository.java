package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.Province;
import org.springframework.stereotype.Repository;

@Repository
public interface ProvinceRepository extends JpaRepositoryCustom<Province, Long>  {
}
