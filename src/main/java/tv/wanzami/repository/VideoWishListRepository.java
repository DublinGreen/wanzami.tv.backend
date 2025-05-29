package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wanzami.model.VideoWishList;

public interface VideoWishListRepository extends JpaRepository<VideoWishList, Long> {
	Iterable<VideoWishList> findByUserId(long userId);
}