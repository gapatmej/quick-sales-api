package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.BranchOffice;
import org.springframework.stereotype.Repository;

@Repository
public interface BranchOfficeRepository extends JpaRepositoryCustom <BranchOffice, Long> {

}
