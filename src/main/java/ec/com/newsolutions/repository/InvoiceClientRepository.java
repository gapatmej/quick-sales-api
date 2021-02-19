package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.domain.enumeration.SRIDocumentStateEnum;
import ec.com.newsolutions.service.dto.ReportInvoiceClientDTO;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InvoiceClientRepository extends JpaRepositoryCustom<InvoiceClient, Long> {

    @Query(" SELECT new ec.com.newsolutions.service.dto.ReportInvoiceClientDTO( " +
        " eD.sriEnvironment, eD.emissionType, eD.accessKey, eD.receiptType, eD.sriDocumentState, " +
        " eD.authorizationDate, iC.businessName, iC.identification, iC.establishmentCode, " +
        " iC.emissionPointCode, iC.sequence, iC.dateIssue, iC.address, iC.phone, iC.email, dIC.mainCode, " +
        " dIC.auxiliaryCode, dIC.description, dIC.quantity, dIC.unitPrice, dIC.discount, dIC.total, " +
        " iC.totalWithoutTax, iC.totalDiscount, iC.totalBaseTaxIVA, iC.totalBaseTaxICE, iC.totalTaxIVA, " +
        " iC.totalBaseTaxICE, iC.tip, iC.total) " +
        " FROM DetailInvoiceClient dIC JOIN InvoiceClient iC on dIC.invoiceClient.id = iC.id " +
        " JOIN ElectronicDocument eD on eD.id = iC.electronicDocument.id" +
        " WHERE iC.id = :idInvoiceClient ")
    List<ReportInvoiceClientDTO> reportInvoiceClient(@Param("idInvoiceClient") Long idInvoiceClient);


}
