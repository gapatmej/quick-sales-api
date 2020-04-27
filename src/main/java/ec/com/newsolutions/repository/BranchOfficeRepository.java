package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.BranchOffice;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the BranchOffice entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BranchOfficeRepository extends JpaRepository<BranchOffice, Long> {

}
