package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.PayWay;

import org.springframework.stereotype.Repository;


@Repository
public interface PayWayRepository extends JpaRepositoryCustom<PayWay, Long> {

}
