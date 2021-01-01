package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.DetailInvoiceClient;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DetailInvoiceClientRepository extends JpaRepositoryCustom<DetailInvoiceClient, Long> {

    void deleteByInvoiceClientId (Long idInvoiceClient);
    void deleteByIdIn(List<Long> ids);
}
