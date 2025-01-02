package tv.wazami.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wazami.model.JwtToken;

/**
 * Jwt Repository Interface
 */
public interface JwtRepository extends JpaRepository<JwtToken, Long> {
	Optional<JwtToken> findByToken(String token);
}