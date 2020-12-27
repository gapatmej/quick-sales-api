package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.DetailInvoiceClient;
import org.springframework.stereotype.Repository;


@Repository
public interface DetailInvoiceRepository extends JpaRepositoryCustom<DetailInvoiceClient, Long> {

}
