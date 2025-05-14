package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.Cast;
import tv.wanzami.model.VideoCast;
import tv.wanzami.repository.VideoCastRepository;

@Component
public class VideoCastMutation implements GraphQLMutationResolver {

	private VideoCastRepository repository;

	public VideoCastMutation(VideoCastRepository repository) {
		this.repository = repository;
	}
	
	public VideoCast createVideoCast(Integer video_id, Long cast_id) {
		VideoCast videoCast = new VideoCast();
		videoCast.setStatus(0);		
		videoCast.setVideo_id(video_id);
		videoCast.setCast(new Cast(cast_id));
		videoCast.setCreated_at(new Date().toInstant());
		repository.save(videoCast);

		return videoCast;
	}
	
	public VideoCast updateVideoCast(Long id, Integer video_id, Long cast_id) throws EntityNotFoundException {
		Optional<VideoCast> optVideoCast = repository.findById(id);

		if (optVideoCast.isPresent()) {
			VideoCast videoCast = optVideoCast.get();

			if (video_id != null)
				videoCast.setVideo_id(video_id);
			
			if (cast_id != null)
				videoCast.setCast(new Cast(cast_id));

			
			videoCast.setUpdated_at(new Date().toInstant());

			repository.save(videoCast);
			return videoCast;
		}

		throw new EntityNotFoundException("Not found Video Cast to update!");
	}

	public VideoCast softDeleteVideoCastById(Long id) throws EntityNotFoundException {
		Optional<VideoCast> optVideoCast = repository.findById(id);

		if (optVideoCast.isPresent()) {
			VideoCast videoCast = optVideoCast.get();
			videoCast.setStatus(0);
			videoCast.setUpdated_at(new Date().toInstant());

			repository.save(videoCast);
			return videoCast;
		}

		throw new EntityNotFoundException("Not found Video Cast to update!");
	}

	public VideoCast setActiveVideoCastById(Long id) throws EntityNotFoundException {
		Optional<VideoCast> optVideoCast = repository.findById(id);

		if (optVideoCast.isPresent()) {
			VideoCast videoCast = optVideoCast.get();
			videoCast.setStatus(1);
			videoCast.setUpdated_at(new Date().toInstant());

			repository.save(videoCast);
			return videoCast;
		}

		throw new EntityNotFoundException("Not found Video Cast to update!");
	}

}
