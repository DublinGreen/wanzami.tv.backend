package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.VideoCategory;
import tv.wanzami.repository.VideoCategoryRepository;

@Component
public class VideoCategoryMutation implements GraphQLMutationResolver {

	private VideoCategoryRepository videoCategoryRepository;

	public VideoCategoryMutation(VideoCategoryRepository videoCategoryRepository) {
		this.videoCategoryRepository = videoCategoryRepository;
	}

	public VideoCategory createVideoCategory(Integer video_id, Integer category_id, Integer status) {
		VideoCategory videoCategory = new VideoCategory();
		videoCategory.setVideo_id(video_id);
		videoCategory.setCategory_id(category_id);
		videoCategory.setStatus(status);

		videoCategory.setCreated_at(new Date().toInstant());
		videoCategoryRepository.save(videoCategory);

		return videoCategory;
	}
	
	public VideoCategory updateVideoCategory(Long id, Integer video_id, Integer category_id) throws EntityNotFoundException {
		Optional<VideoCategory> optVideoCategoryRepository = this.videoCategoryRepository.findById(id);

		if (optVideoCategoryRepository.isPresent()) {
			VideoCategory videoCategory = optVideoCategoryRepository.get();

			if (video_id != 0 && category_id != 0)
				videoCategory.setVideo_id(video_id);
				videoCategory.setCategory_id(category_id);
				videoCategory.setUpdated_at(new Date().toInstant());

				videoCategoryRepository.save(videoCategory);
			return videoCategory;
		}

		throw new EntityNotFoundException("Not found videoCategory to update!");
	}

	public VideoCategory softDeleteVideoCategoryByIdById(Long id) throws EntityNotFoundException {
		Optional<VideoCategory> optVideoCategory = videoCategoryRepository.findById(id);

		if (optVideoCategory.isPresent()) {
			VideoCategory videoCategory = optVideoCategory.get();
			videoCategory.setStatus(0);
			videoCategory.setUpdated_at(new Date().toInstant());
			videoCategoryRepository.save(videoCategory);
			return videoCategory;
		}

		throw new EntityNotFoundException("Not found Video Category to update!");
	}

	public VideoCategory setActiveVideoCategoryById(Long id) throws EntityNotFoundException {
		Optional<VideoCategory> optVideoCategory = videoCategoryRepository.findById(id);

		if (optVideoCategory.isPresent()) {
			VideoCategory videoCategory = optVideoCategory.get();
			videoCategory.setStatus(1);
			videoCategory.setUpdated_at(new Date().toInstant());
			videoCategoryRepository.save(videoCategory);
			return videoCategory;
		}

		throw new EntityNotFoundException("Not found Video Category to update!");
	}

}
