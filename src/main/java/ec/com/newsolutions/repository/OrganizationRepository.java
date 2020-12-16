package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.Organization;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@SuppressWarnings("unused")
@Repository
public interface OrganizationRepository extends JpaRepositoryCustom<Organization, Long> {

}
