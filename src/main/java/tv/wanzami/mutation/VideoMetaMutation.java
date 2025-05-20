package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.Video;
import tv.wanzami.model.VideoMeta;
import tv.wanzami.repository.VideoMetaRepository;

@Component
public class VideoMetaMutation implements GraphQLMutationResolver {

	private VideoMetaRepository repository;

	public VideoMetaMutation(VideoMetaRepository repository) {
		this.repository = repository;
	}
	
	public VideoMeta createVideoMeta(Integer video_id, String length, String videoQuality, String videoUrl, String videoTrailerUrl) {
		VideoMeta videoMeta = new VideoMeta();
		videoMeta.setVideo(new Video((long) video_id));
		videoMeta.setVideo_length(length);
		videoMeta.setVideo_url(videoUrl);
		videoMeta.setVideo_trailer_url(videoTrailerUrl);
		videoMeta.setVideo_quanlity(videoQuality);
		videoMeta.setCreated_at(new Date().toInstant());
		repository.save(videoMeta);

		return videoMeta;
	}
	
	public VideoMeta updateVideoMeta(Long id, Integer video_id, String length, String videoQuality, String videoUrl, String videoTrailerUrl) throws EntityNotFoundException {
		Optional<VideoMeta> optVideoMeta = repository.findById(id);

		if (optVideoMeta.isPresent()) {
			VideoMeta videoMeta = optVideoMeta.get();

			if (video_id != null)
				videoMeta.setVideo(new Video((long) video_id));
			
			if (length != null)
				videoMeta.setVideo_length(length);
			
			if (videoQuality != null)
				videoMeta.setVideo_quanlity(videoQuality);
			
			if (videoUrl != null)
				videoMeta.setVideo_url(videoUrl);
			
			if (videoTrailerUrl != null)
				videoMeta.setVideo_trailer_url(videoTrailerUrl);
			
			videoMeta.setUpdated_at(new Date().toInstant());

			repository.save(videoMeta);
			return videoMeta;
		}

		throw new EntityNotFoundException("Not found Video Meta to update!");
	}

}
