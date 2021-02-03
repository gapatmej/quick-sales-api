package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.BranchOffice;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchOfficeRepository extends JpaRepositoryCustom <BranchOffice, Long> {

}
