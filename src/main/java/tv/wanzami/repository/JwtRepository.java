package tv.wanzami.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import tv.wanzami.model.JwtToken;

/**
 * Jwt Repository Interface
 */

@Repository
public interface JwtRepository extends JpaRepository<JwtToken, Long> {
	
	Optional<JwtToken> findByJwt(String jwt);

	Optional<JwtToken> findByStatusAndJwt(Integer status,String jwt);

}