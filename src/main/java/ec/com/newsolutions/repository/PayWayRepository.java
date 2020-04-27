package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.PayWay;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the PayWay entity.
 */
@SuppressWarnings("unused")
@Repository
public interface PayWayRepository extends JpaRepository<PayWay, Long> {

}
