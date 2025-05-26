package tv.wanzami.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tv.wanzami.model.VideoPayment;

public interface VideoPaymentRepository extends JpaRepository<VideoPayment, Long> {
	
	@Query("SELECT vc FROM VideoPayment vc WHERE vc.videoId = :videoId")
	List<VideoPayment> findVideoPaymentByVideoId(@Param("videoId") Long videoId);
	
	@Query("SELECT vc FROM VideoPayment vc WHERE vc.email = :email")
	List<VideoPayment> findVideoPaymentByEmail(@Param("email") String email);
}