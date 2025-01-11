package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import graphql.kickstart.servlet.context.GraphQLServletContext;
import graphql.kickstart.tools.GraphQLMutationResolver;
import graphql.schema.DataFetchingEnvironment;
import io.jsonwebtoken.io.IOException;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.Author;
import tv.wanzami.model.Category;
import tv.wanzami.model.Video;
import tv.wanzami.repository.VideoRepository;
import tv.wanzami.service.FileUploadResolver;

@Component
public class VideoMutation implements GraphQLMutationResolver {

	private VideoRepository videoRespository;

	public VideoMutation(VideoRepository videoRespository) {
		this.videoRespository = videoRespository;
	}

	public Video createVideo(int categoryId, int authorId, int status, String name, String description, String short_description, String thumbnail) {
		Video video = new Video();
		video.setCategory(new Category((long) categoryId));
		video.setAuthor(new Author((long) authorId));
		video.setStatus(0);
		video.setName(name);
		video.setDescription(description);
		video.setShort_description(short_description);
		video.setThumbnail(thumbnail);
		video.setCreated_at(new Date().toInstant());

		videoRespository.save(video);

		return video;
	}

	public Video updateVideo(Long id, int categoryId, int authorId, int status, 
			String name, String description, String short_description, String thumbnail) throws EntityNotFoundException {
		Optional<Video> optVideo = videoRespository.findById(id);

		if (optVideo.isPresent()) {
			Video video = optVideo.get();

			if (categoryId != 0)
				video.setCategory(new Category((long) categoryId));
			
			if (authorId != 0)
				video.setAuthor(new Author((long) authorId));
			
			if (status != 0)
				video.setStatus(status);
			
			if (name != null)
				video.setName(name);
			
			if (description != null)
				video.setDescription(description);
			
			if (thumbnail != null)
				video.setThumbnail(thumbnail);

			videoRespository.save(video);
			return video;
		}

		throw new EntityNotFoundException("Not found User to update!");
	}
	
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
	
	
    public String uploadFile(DataFetchingEnvironment environment) throws IOException, java.io.IOException {
        GraphQLServletContext context = environment.getContext();
        MultipartFile file = (MultipartFile) context.getFileParts().get(0);

        // Process the file (save it, analyze it, etc.)
        String originalFilename = file.getOriginalFilename();
        byte[] content = file.getBytes();

        // For demo purposes, return the file name
        return "Uploaded file: " + originalFilename;
    }


}
