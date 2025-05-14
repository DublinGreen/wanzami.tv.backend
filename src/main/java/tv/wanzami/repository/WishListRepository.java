package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tv.wanzami.model.WishList;

public interface WishListRepository extends JpaRepository<WishList, Long> {

}