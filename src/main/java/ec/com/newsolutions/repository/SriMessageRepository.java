package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.SriMessage;
import org.springframework.stereotype.Repository;

@Repository
public interface SriMessageRepository extends JpaRepositoryCustom<SriMessage, Long> {
}
