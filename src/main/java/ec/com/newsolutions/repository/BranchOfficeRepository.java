package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.BranchOffice;
import org.springframework.stereotype.Repository;

@SuppressWarnings("unused")
@Repository
public interface BranchOfficeRepository extends JpaRepositoryCustom <BranchOffice, Long> {

}
