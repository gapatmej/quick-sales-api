package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.EmissionPoint;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmissionPointRepository extends JpaRepositoryCustom<EmissionPoint, Long> {

    void deleteByBranchOfficeId(Long branchOfficeId);
}
