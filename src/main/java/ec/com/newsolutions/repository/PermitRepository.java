package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.Permit;
import org.springframework.stereotype.Repository;

@Repository
public interface PermitRepository extends JpaRepositoryCustom<Permit,Long> {
}
