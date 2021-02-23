package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.AddressCompany;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressCompanyRepository extends JpaRepositoryCustom<AddressCompany,Long>{

    void deleteByCompanyId(Long id);
    List<AddressCompany> findByCompanyId(Long companyId);
}
