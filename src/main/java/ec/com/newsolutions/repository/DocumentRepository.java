package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.Document;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepositoryCustom<Document,Long> {
}
