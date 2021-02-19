package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.ElectronicDocument;
import ec.com.newsolutions.domain.enumeration.SRIDocumentStateEnum;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.Instant;

public interface ElectronicDocumentRepository extends JpaRepositoryCustom<ElectronicDocument, Long> {

    @Modifying
    @Query("update ElectronicDocument eD set eD.sriDocumentState = :sriDocumentStateEnum where eD.id = :electronicDocumentId")
    void updateSriDocumentState(@Param("sriDocumentStateEnum") SRIDocumentStateEnum sriDocumentStateEnum, @Param("electronicDocumentId")  Long electronicDocumentId);

    @Modifying
    @Query("update ElectronicDocument eD set eD.authorizationDate = :authorizationDate, eD.sriDocumentState = :sriDocumentStateEnum where eD.id = :electronicDocumentId")
    void updateSriAuthorizedFields(@Param("electronicDocumentId")  Long electronicDocumentId, @Param("sriDocumentStateEnum") SRIDocumentStateEnum sriDocumentStateEnum,
                                   @Param("authorizationDate") Instant authorizationDate);
}
