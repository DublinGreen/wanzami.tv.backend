package tv.wazami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wazami.model.Cast;

public interface CastRepository extends JpaRepository<Cast, Long> {
	Iterable<Cast> findByVideoId(Long id);
}