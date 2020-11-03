package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.Authority;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Spring Data JPA repository for the {@link Authority} entity.
 */
public interface AuthorityRepository extends JpaRepositoryCustom<Authority, Long> {
}
