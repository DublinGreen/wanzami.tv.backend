package tv.wazami.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tv.wazami.model.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

}