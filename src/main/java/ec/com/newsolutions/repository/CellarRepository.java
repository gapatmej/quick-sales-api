package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.Cellar;

import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data  repository for the Cellar entity.
 */
@SuppressWarnings("unused")
@Repository
public interface CellarRepository extends JpaRepository<Cellar, Long> {

}
