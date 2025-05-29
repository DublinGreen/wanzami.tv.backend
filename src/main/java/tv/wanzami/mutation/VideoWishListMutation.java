package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.User;
import tv.wanzami.model.Video;
import tv.wanzami.model.VideoWishList;
import tv.wanzami.repository.UserRepository;
import tv.wanzami.repository.VideoWishListRepository;

@Component
public class VideoWishListMutation implements GraphQLMutationResolver {

	private VideoWishListRepository repository;
	private UserRepository userRepository;

	public VideoWishListMutation(VideoWishListRepository repository, UserRepository userRepository) {
		this.repository = repository;
		this.userRepository = userRepository;
	}
	
	public VideoWishList createWishList(Long video_id, String email) {
		VideoWishList wishList = new VideoWishList();

		Optional<User> user = userRepository.findByEmail(email);
		if(user.isPresent()) {
			wishList = new VideoWishList();
			
			wishList.setVideo(new Video(video_id));
			wishList.setUser(new User(user.get().getId()));
			wishList.setStatus(1);
			wishList.setCreated_at(new Date().toInstant());
			
			repository.save(wishList);	
		}
		
		return wishList;
	}
	
	public VideoWishList updateWishList(Long id, Long video_id, Long user_id) throws EntityNotFoundException {
		Optional<VideoWishList> optWishList = repository.findById(id);

		if (optWishList.isPresent()) {
			VideoWishList wishList = optWishList.get();

			if (video_id != null)
				wishList.setVideo(new Video(video_id));
			
			if (user_id != null)
				wishList.setUser(new User(user_id));
			
			wishList.setUpdated_at(new Date().toInstant());

			repository.save(wishList);
			return wishList;
		}

		throw new EntityNotFoundException("Not found Video WishList to update!");
	}

	public VideoWishList softDeleteWishListById(Long id) throws EntityNotFoundException {
		Optional<VideoWishList> opt = repository.findById(id);

		if (opt.isPresent()) {
			VideoWishList wishList = opt.get();
			wishList.setStatus(0);
			wishList.setUpdated_at(new Date().toInstant());

			repository.save(wishList);
			return wishList;
		}

		throw new EntityNotFoundException("Not found Video WishList to update!");
	}

	public VideoWishList setActiveWishListById(Long id) throws EntityNotFoundException {
		Optional<VideoWishList> optWishList = repository.findById(id);

		if (optWishList.isPresent()) {
			VideoWishList wishList = optWishList.get();
			wishList.setStatus(1);
			wishList.setUpdated_at(new Date().toInstant());

			repository.save(wishList);
			return wishList;
		}

		throw new EntityNotFoundException("Not found Video WishList to update!");
	}

}
