package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tv.wanzami.model.VideoCategory;

public interface VideoCategoryRepository extends JpaRepository<VideoCategory, Long> {

}