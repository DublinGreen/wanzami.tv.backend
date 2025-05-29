package tv.wanzami.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wanzami.model.UserMeta;

public interface UserMetaRepository extends JpaRepository<UserMeta, Long> {
	
	Optional<UserMeta> findByEmail(String Email);

}