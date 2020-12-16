package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.Category;
import org.springframework.stereotype.Repository;


@Repository
public interface CategoryRepository extends JpaRepositoryCustom<Category, Long> {

}
