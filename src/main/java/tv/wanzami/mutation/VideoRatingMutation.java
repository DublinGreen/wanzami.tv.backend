package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.VideoRating;
import tv.wanzami.repository.VideoRatingRepository;

@Component
public class VideoRatingMutation implements GraphQLMutationResolver {

	private VideoRatingRepository repository;

	public VideoRatingMutation(VideoRatingRepository repository) {
		this.repository = repository;
	}
	
	public VideoRating createVideoRating(String rating, String description) {
		VideoRating videoRating = new VideoRating();
		videoRating.setRating(rating);
		videoRating.setDescription(description);
		videoRating.setStatus(0);		
		videoRating.setCreated_at(new Date().toInstant());
		repository.save(videoRating);

		return videoRating;
	}
	
	public VideoRating updateVideoRating(Long id, String rating, String description) throws EntityNotFoundException {
		Optional<VideoRating> optVideoRating = repository.findById(id);

		if (optVideoRating.isPresent()) {
			VideoRating videoRating = optVideoRating.get();

			if (rating != null)
				videoRating.setRating(rating);
			
			if (description != null)
				videoRating.setDescription(description);
			
			videoRating.setUpdated_at(new Date().toInstant());

			repository.save(videoRating);
			return videoRating;
		}

		throw new EntityNotFoundException("Not found videoRating to update!");
	}

	public VideoRating softDeleteVideoRatingById(Long id) throws EntityNotFoundException {
		Optional<VideoRating> optVideoRating = repository.findById(id);

		if (optVideoRating.isPresent()) {
			VideoRating videoRating = optVideoRating.get();
			videoRating.setStatus(0);
			videoRating.setUpdated_at(new Date().toInstant());

			repository.save(videoRating);
			return videoRating;
		}

		throw new EntityNotFoundException("Not found VideoRating to update!");
	}

	public VideoRating setActiveVideoRatingById(Long id) throws EntityNotFoundException {
		Optional<VideoRating> optVideoRating = repository.findById(id);

		if (optVideoRating.isPresent()) {
			VideoRating videoRating = optVideoRating.get();
			videoRating.setStatus(1);
			videoRating.setUpdated_at(new Date().toInstant());

			repository.save(videoRating);
			return videoRating;
		}

		throw new EntityNotFoundException("Not found VideoRating to update!");
	}

}
