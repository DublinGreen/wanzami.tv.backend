package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.Country;
import tv.wanzami.model.Video;
import tv.wanzami.model.VideoCountryRestriction;
import tv.wanzami.repository.VideoCountryRestrictionRepository;

@Component
public class VideoCountryRestrictionMutation implements GraphQLMutationResolver {

	private VideoCountryRestrictionRepository videoCountryRestrictionRepository;

	public VideoCountryRestrictionMutation(VideoCountryRestrictionRepository videoCountryRestrictionRepository) {
		this.videoCountryRestrictionRepository = videoCountryRestrictionRepository;
	}

	public VideoCountryRestriction createVideoCountryRestriction(String note, Integer video_id, Integer country_id) {
		VideoCountryRestriction videoCountryRestriction = new VideoCountryRestriction();
		videoCountryRestriction.setNote(note);
		videoCountryRestriction.setStatus(0);
		videoCountryRestriction.setVideo(new Video((long) video_id));
		videoCountryRestriction.setCountry(new Country((long) country_id));
		videoCountryRestriction.setCreated_at(new Date().toInstant());

		videoCountryRestrictionRepository.save(videoCountryRestriction);

		return videoCountryRestriction;
	}

	public VideoCountryRestriction updateVideoCountryRestriction(Long id, String note, Integer video_id, Integer country_id) throws EntityNotFoundException {
		Optional<VideoCountryRestriction> optVideoCountryRestriction = videoCountryRestrictionRepository.findById(id);

		if (optVideoCountryRestriction.isPresent()) {
			VideoCountryRestriction videoCountryRestriction = optVideoCountryRestriction.get();

			if (note != null)
				videoCountryRestriction.setNote(note);
			
			if (video_id != null)
				videoCountryRestriction.setVideo(new Video((long) video_id));
			
			if (country_id != null)
				videoCountryRestriction.setCountry(new Country((long) country_id));

			videoCountryRestriction.setUpdated_at(new Date().toInstant());

			videoCountryRestrictionRepository.save(videoCountryRestriction);
			return videoCountryRestriction;
		}

		throw new EntityNotFoundException("Not found Country to update!");
	}
	
	public VideoCountryRestriction softDeleteVideoCountryRestrictionById(Long id) throws EntityNotFoundException {
		Optional<VideoCountryRestriction> optVideoCountryRestriction = videoCountryRestrictionRepository.findById(id);

		if (optVideoCountryRestriction.isPresent()) {
			VideoCountryRestriction videoCountryRestriction = optVideoCountryRestriction.get();
			videoCountryRestriction.setStatus(0);
			videoCountryRestriction.setUpdated_at(new Date().toInstant());

			videoCountryRestrictionRepository.save(videoCountryRestriction);
			return videoCountryRestriction;
		}

		throw new EntityNotFoundException("Not found Video Country Restriction to update!");
	}

	public VideoCountryRestriction setActiveVideoCountryRestrictionById(Long id) throws EntityNotFoundException {
		Optional<VideoCountryRestriction> optVideoCountryRestriction = videoCountryRestrictionRepository.findById(id);

		if (optVideoCountryRestriction.isPresent()) {
			VideoCountryRestriction videoCountryRestriction = optVideoCountryRestriction.get();
			videoCountryRestriction.setStatus(1);
			videoCountryRestriction.setUpdated_at(new Date().toInstant());

			videoCountryRestrictionRepository.save(videoCountryRestriction);
			return videoCountryRestriction;
		}

		throw new EntityNotFoundException("Not found Video Country Restriction to update!");
	}

}
