package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Component;
import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.Author;
import tv.wanzami.model.Category;
import tv.wanzami.model.Video;
import tv.wanzami.model.VideoMeta;
import tv.wanzami.model.VideoRating;
import tv.wanzami.repository.VideoRepository;

@Component
@CacheConfig(cacheNames = {"videos"})
public class VideoMutation implements GraphQLMutationResolver {

	private VideoRepository videoRespository;

	public VideoMutation(VideoRepository videoRespository) {
		this.videoRespository = videoRespository;
	}

    @CacheEvict(allEntries = true)  // Clear all cached videos on create
	public Video createVideo(int categoryId, int authorId, int video_rating, int status, String name, String description,
			String short_description, String thumbnail,String video_short_url, String banner, String reviewsRating) {
		Video video = new Video();
		video.setCategory(new Category((long) categoryId));
		video.setAuthor(new Author((long) authorId));
		video.setVideoRating(new VideoRating((long) video_rating));
		video.setVideo_short_url(video_short_url);
		video.setStatus(0);
		video.setName(name);
		video.setDescription(description);
		video.setShort_description(short_description);
		video.setBanner(banner);
		video.setThumbnail(thumbnail);
		video.setReviews_rating(reviewsRating);
		video.setCreated_at(new Date().toInstant());
		videoRespository.save(video);

		return video;
	}
	
    @CachePut(key = "#id")  // Update cache entry when adding video meta
	public Video addVideoMetaToVideo(Long id, int videoMetaId) {
		Optional<Video> optVideo = videoRespository.findById(id);

		if (optVideo.isPresent()) {
			Video video = optVideo.get();

			if (videoMetaId != 0)
				video.setVideoMeta(new VideoMeta((long) videoMetaId));
			
			videoRespository.save(video);
			return video;
		}

		throw new EntityNotFoundException("Not found User to update!");
	}

    @CachePut(key = "#id") // Update cache entry for this video ID on update
	public Video updateVideo(Long id, int categoryId, int authorId, int video_rating,  int status, String name, String description,
			String short_description, String thumbnail, String video_short_url, String banner, String reviewsRating, int videoMetaId) throws EntityNotFoundException {
		Optional<Video> optVideo = videoRespository.findById(id);

		if (optVideo.isPresent()) {
			Video video = optVideo.get();

			if (categoryId != 0)
				video.setCategory(new Category((long) categoryId));

			if (authorId != 0)
				video.setAuthor(new Author((long) authorId));
			
			if (video_rating != 0)
				video.setVideoRating(new VideoRating((long) video_rating));

			if (status != 0)
				video.setStatus(status);

			if (name != null)
				video.setName(name);

			if (description != null)
				video.setDescription(description);

			if (thumbnail != null)
				video.setThumbnail(thumbnail);
			
			if (video_short_url != null)
				video.setVideo_short_url(video_short_url);
			
			if (banner != null)
				video.setBanner(banner);
			
			if (reviewsRating != null)
				video.setReviews_rating(reviewsRating);
			
			if (videoMetaId != 0)
				video.setVideoMeta(new VideoMeta((long) videoMetaId));
			
			videoRespository.save(video);
			return video;
		}

		throw new EntityNotFoundException("Not found User to update!");
	}
	
    @CacheEvict(key = "#id")  // Evict this video's cache on soft delete
	public Video softDeleteVideoById(Long id) throws EntityNotFoundException {
		Optional<Video> optVideo = videoRespository.findById(id);

		if (optVideo.isPresent()) {
			Video video = optVideo.get();
			video.setStatus(0);
			video.setUpdated_at(new Date().toInstant());

			videoRespository.save(video);
			return video;
		}

		throw new EntityNotFoundException("Not found Video to update!");
	}

    @CacheEvict(key = "#id") // Evict this video's cache on set active
	public Video setActiveVideoById(Long id) throws EntityNotFoundException {
		Optional<Video> optVideo = videoRespository.findById(id);

		if (optVideo.isPresent()) {
			Video video = optVideo.get();
			video.setStatus(1);
			video.setUpdated_at(new Date().toInstant());

			videoRespository.save(video);
			return video;
		}

		throw new EntityNotFoundException("Not found Video to update!");
	}
	
}
