package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tv.wanzami.model.VideoCast;

public interface VideoCastRepository extends JpaRepository<VideoCast, Long> {
}