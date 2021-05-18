package ec.com.newsolutions.repository.impl;

import ec.com.newsolutions.domain.DetailInvoiceClient;
import ec.com.newsolutions.domain.InvoiceClient;
import ec.com.newsolutions.repository.CustomInvoiceClientRepository;
import ec.com.newsolutions.service.dto.BatchElectronicDocumentDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.Tuple;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

public class CustomInvoiceClientRepositoryImpl implements CustomInvoiceClientRepository {

    @PersistenceContext
    private EntityManager entityManager;

    private static final String UNSORTED = "UNSORTED";

    @Override
    public Page<BatchElectronicDocumentDTO> getPendientElectronicDocument(Pageable pageable) {
        StringBuilder sqlSelectCountQuery = new StringBuilder(" select count(iC) ");
        StringBuilder sqlSelectQuery = new StringBuilder(" SELECT distinct iC ");

        StringBuilder sqlSelect = new StringBuilder(" FROM InvoiceClient iC JOIN DetailInvoiceClient dIC on dIC.invoiceClient.id = iC.id ");
        sqlSelect.append(" where 1 = 1 and iC.id = 4401");

        sqlSelectQuery.append(sqlSelect);
        sqlSelectCountQuery.append(sqlSelect);

        if (pageable != null && !UNSORTED.equals(pageable.getSort().toString())) {
            sqlSelectQuery.append("order by ");
            sqlSelectQuery.append(pageable.getSort().toString().replace(":", ""));
        }

        TypedQuery<InvoiceClient> querySelect = entityManager.createQuery(sqlSelectQuery.toString(), InvoiceClient.class);
        Query queryCount = entityManager.createQuery(sqlSelectCountQuery.toString());

        querySelect.setFirstResult(pageable.getPageNumber() * pageable.getPageSize());
        querySelect.setMaxResults(pageable.getPageSize());

        int total = Integer.parseInt(queryCount.getSingleResult().toString());
        List<InvoiceClient> a = querySelect.getResultList();
        return null;
    }


    private List<BatchElectronicDocumentDTO> mappingToDTO(List<Tuple> results) {
        List<BatchElectronicDocumentDTO> response = new ArrayList<>();
        for (Tuple tuple : results) {
            response.add(new BatchElectronicDocumentDTO(tuple.get("businessName").toString(),tuple.get("identification").toString(),
                (DetailInvoiceClient)tuple.get("detailsInvoiceClient") ));
        }

        return response;
    }
}
