package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.InvoiceClient;
import org.springframework.stereotype.Repository;


@Repository
public interface InvoiceClientRepository extends JpaRepositoryCustom<InvoiceClient, Long> {

}
