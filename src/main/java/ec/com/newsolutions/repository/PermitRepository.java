package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.Permit;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermitRepository extends JpaRepositoryCustom<Permit,Long> {

    @Query(" select distinct p from Permit p " +
        " inner join fetch p.authorities a " +
        " where a.id in :authorityIds " )
    List<Permit> findAllByAuthoritiesIn(@Param("authorityIds") List<Long> authorityIds);
}
