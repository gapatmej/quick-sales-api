package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.Organization;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data  repository for the Organization entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrganizationRepository extends JpaRepositoryCustom<Organization, Long> {

    @Query("select o from Organization o inner join User u where u.organization = o and u.login =:userLogin  ")
    Optional<Organization> findOneByUserLogin(@Param("userLogin") String userLogin);

}
