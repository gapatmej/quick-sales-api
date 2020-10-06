package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.Category;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;


@SuppressWarnings("unused")
@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
