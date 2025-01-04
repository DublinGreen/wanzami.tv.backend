package tv.wazami.repository;

//import java.util.Collection;
import java.util.Optional;

//import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
//import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import tv.wazami.model.JwtToken;

/**
 * Jwt Repository Interface
 */

@Repository
public interface JwtRepository extends JpaRepository<JwtToken, Long> {
	
	@Query(value = "SELECT * FROM jwt_token WHERE jwt = :jwt", nativeQuery = true)
	Optional<JwtToken> findByJwtNative(@Param("jwt") String jwt);
	
	@Query("SELECT j FROM JwtToken j WHERE j.status = 1")
	Optional<JwtToken> findAllActiveStatus();

	@Query("SELECT j FROM JwtToken j WHERE j.status = :status AND j.jwt = :jwt")
	Optional<JwtToken> findByStatusAndJwt(@Param("status") Integer status, @Param("jwt") String jwt);

}