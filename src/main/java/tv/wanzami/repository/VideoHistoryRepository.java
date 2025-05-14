package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tv.wanzami.model.VideoHistory;

public interface VideoHistoryRepository extends JpaRepository<VideoHistory, Long> {

}