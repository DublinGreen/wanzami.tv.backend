package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.User;
import tv.wanzami.model.Video;
import tv.wanzami.model.WishList;
import tv.wanzami.repository.WishListRepository;

@Component
public class WishListMutation implements GraphQLMutationResolver {

	private WishListRepository repository;

	public WishListMutation(WishListRepository repository) {
		this.repository = repository;
	}
	
	public WishList createWishList(Long video_id, Long user_id) {
		WishList wishList = new WishList();
		
		wishList.setVideo(new Video(video_id));
		wishList.setUser(new User(user_id));
		wishList.setStatus(0);
		wishList.setCreated_at(new Date().toInstant());
		
		repository.save(wishList);

		return wishList;
	}
	
	public WishList updateWishList(Long id, Long video_id, Long user_id) throws EntityNotFoundException {
		Optional<WishList> optWishList = repository.findById(id);

		if (optWishList.isPresent()) {
			WishList wishList = optWishList.get();

			if (video_id != null)
				wishList.setVideo(new Video(video_id));
			
			if (user_id != null)
				wishList.setUser(new User(user_id));
			
			wishList.setUpdated_at(new Date().toInstant());

			repository.save(wishList);
			return wishList;
		}

		throw new EntityNotFoundException("Not found Video History to update!");
	}

	public WishList softDeleteWishListById(Long id) throws EntityNotFoundException {
		Optional<WishList> opt = repository.findById(id);

		if (opt.isPresent()) {
			WishList wishList = opt.get();
			wishList.setStatus(0);
			wishList.setUpdated_at(new Date().toInstant());

			repository.save(wishList);
			return wishList;
		}

		throw new EntityNotFoundException("Not found WishList to update!");
	}

	public WishList setActiveWishListById(Long id) throws EntityNotFoundException {
		Optional<WishList> optWishList = repository.findById(id);

		if (optWishList.isPresent()) {
			WishList wishList = optWishList.get();
			wishList.setStatus(1);
			wishList.setUpdated_at(new Date().toInstant());

			repository.save(wishList);
			return wishList;
		}

		throw new EntityNotFoundException("Not found WishList to update!");
	}

}
