package tv.wanzami.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tv.wanzami.model.Video;

public interface VideoRepository extends JpaRepository<Video, Long> {

    @Query("SELECT v FROM Video v WHERE LOWER(v.name) LIKE LOWER(CONCAT('%', :name, '%'))")
    List<Video> searchByNameLike(@Param("name") String name);
}