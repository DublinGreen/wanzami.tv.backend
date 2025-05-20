package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.VideoProductionCrew;
import tv.wanzami.repository.VideoProductionCrewRepository;

@Component
public class VideoProductionCrewMutation implements GraphQLMutationResolver {

	private VideoProductionCrewRepository repository;

	public VideoProductionCrewMutation(VideoProductionCrewRepository repository) {
		this.repository = repository;
	}
	
	public VideoProductionCrew createVideoProductionCrew(Integer video_id, String position, String name) {
		VideoProductionCrew videoProductionCrew = new VideoProductionCrew();
		videoProductionCrew.setStatus(0);		
		videoProductionCrew.setVideo_id(video_id);
		videoProductionCrew.setName(name);
		videoProductionCrew.setPosition(position);
		videoProductionCrew.setCreated_at(new Date().toInstant());
		repository.save(videoProductionCrew);

		return videoProductionCrew;
	}
	
	public VideoProductionCrew updateVideoProductionCrew(Long id, Integer video_id, String position, String name) throws EntityNotFoundException {
		Optional<VideoProductionCrew> optVideoProductionCrew = repository.findById(id);

		if (optVideoProductionCrew.isPresent()) {
			VideoProductionCrew videoProductionCrew = optVideoProductionCrew.get();

			if (video_id != null)
				videoProductionCrew.setVideo_id(video_id);
			
			if (position != null)
				videoProductionCrew.setPosition(position);
			
			if (name != null)
				videoProductionCrew.setName(name);

			videoProductionCrew.setUpdated_at(new Date().toInstant());

			repository.save(videoProductionCrew);
			return videoProductionCrew;
		}

		throw new EntityNotFoundException("Not found Video Production Crew to update!");
	}

	public VideoProductionCrew softDeleteVideoProductionCrewById(Long id) throws EntityNotFoundException {
		Optional<VideoProductionCrew> optVideoProductionCrew = repository.findById(id);

		if (optVideoProductionCrew.isPresent()) {
			VideoProductionCrew videoProductionCrew = optVideoProductionCrew.get();
			videoProductionCrew.setStatus(0);
			videoProductionCrew.setUpdated_at(new Date().toInstant());

			repository.save(videoProductionCrew);
			return videoProductionCrew;
		}

		throw new EntityNotFoundException("Not found Video Production Crew to update!");
	}

	public VideoProductionCrew setActiveVideoProductionCrewById(Long id) throws EntityNotFoundException {
		Optional<VideoProductionCrew> optVideoProductionCrew = repository.findById(id);

		if (optVideoProductionCrew.isPresent()) {
			VideoProductionCrew videoProductionCrew = optVideoProductionCrew.get();
			videoProductionCrew.setStatus(1);
			videoProductionCrew.setUpdated_at(new Date().toInstant());

			repository.save(videoProductionCrew);
			return videoProductionCrew;
		}

		throw new EntityNotFoundException("Not found Video Production Crew to update!");
	}

}
