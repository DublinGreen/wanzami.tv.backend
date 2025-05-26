package tv.wanzami.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tv.wanzami.model.VideoPrice;

public interface VideoPriceRepository extends JpaRepository<VideoPrice, Long> {
	
	@Query("SELECT vc FROM VideoPrice vc WHERE vc.videoId = :videoId")
	List<VideoPrice> findVideoPricesByVideoId(@Param("videoId") Long videoId);
}