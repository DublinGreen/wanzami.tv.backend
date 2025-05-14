package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.User;
import tv.wanzami.model.Video;
import tv.wanzami.model.VideoHistory;
import tv.wanzami.repository.VideoHistoryRepository;

@Component
public class VideoHistoryMutation implements GraphQLMutationResolver {

	private VideoHistoryRepository repository;

	public VideoHistoryMutation(VideoHistoryRepository repository) {
		this.repository = repository;
	}
	
	public VideoHistory createVideoHistory(Long video_id, Long user_id, Integer rating, String film_duration_watched) {
		VideoHistory videoHistory = new VideoHistory();
		
		videoHistory.setFinished_film(0);
		videoHistory.setVideo(new Video(video_id));
		videoHistory.setUser(new User(user_id));
		videoHistory.setRating(rating);
		videoHistory.setFilm_duration_watched(film_duration_watched);
		videoHistory.setCreated_at(new Date().toInstant());
		
		repository.save(videoHistory);

		return videoHistory;
	}
	
	public VideoHistory updateVideoHistory(Long id, Long video_id, Long user_id, Integer rating, String film_duration_watched) throws EntityNotFoundException {
		Optional<VideoHistory> optVideoHistory = repository.findById(id);

		if (optVideoHistory.isPresent()) {
			VideoHistory videoHistory = optVideoHistory.get();

			if (video_id != null)
				videoHistory.setVideo(new Video(video_id));
			
			if (user_id != null)
				videoHistory.setUser(new User(user_id));
			
			if (rating != null)
				videoHistory.setRating(rating);
			
			if (film_duration_watched != null)
				videoHistory.setFilm_duration_watched(film_duration_watched);
			
			videoHistory.setUpdated_at(new Date().toInstant());

			repository.save(videoHistory);
			return videoHistory;
		}

		throw new EntityNotFoundException("Not found Video History to update!");
	}

	public VideoHistory markFilmAsUnfinished(Long id) throws EntityNotFoundException {
		Optional<VideoHistory> optVideoHistory = repository.findById(id);

		if (optVideoHistory.isPresent()) {
			VideoHistory videoHistory = optVideoHistory.get();
			videoHistory.setFinished_film(0);
			videoHistory.setUpdated_at(new Date().toInstant());

			repository.save(videoHistory);
			return videoHistory;
		}

		throw new EntityNotFoundException("Not found Video History to update!");
	}

	public VideoHistory markFilmAsFinished(Long id) throws EntityNotFoundException {
		Optional<VideoHistory> optVideoHistory = repository.findById(id);

		if (optVideoHistory.isPresent()) {
			VideoHistory videoHistory = optVideoHistory.get();
			videoHistory.setFinished_film(1);
			videoHistory.setUpdated_at(new Date().toInstant());

			repository.save(videoHistory);
			return videoHistory;
		}

		throw new EntityNotFoundException("Not found Video History to update!");
	}

}
