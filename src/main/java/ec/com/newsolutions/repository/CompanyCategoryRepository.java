package ec.com.newsolutions.repository;
import ec.com.newsolutions.domain.CompanyCategory;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyCategoryRepository extends JpaRepositoryCustom<CompanyCategory,Long> {
}
