package ec.com.newsolutions.repository;

import ec.com.newsolutions.domain.EmissionPointUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmissionPointUserRepository extends JpaRepository<EmissionPointUser, Long> {

    List<EmissionPointUser> findEmissionPointUserByUser_Login(String login);
}
