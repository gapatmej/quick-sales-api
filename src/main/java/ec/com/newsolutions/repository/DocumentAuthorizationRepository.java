package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.DocumentAuthorization;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentAuthorizationRepository extends JpaRepositoryCustom<DocumentAuthorization,Long> {

    void deleteByDocumentId(Long documentId);
}
