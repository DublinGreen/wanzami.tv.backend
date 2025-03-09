package tv.wanzami.mutation;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.Cast;
import tv.wanzami.model.Video;
import tv.wanzami.repository.CastRepository;

@Component
@CrossOrigin(origins = "http://localhost:3000")
public class CastMutation implements GraphQLMutationResolver {

	private CastRepository castRepository;

	public CastMutation(CastRepository castRepository) {
		this.castRepository = castRepository;
	}
	
	public Cast createCast(String name, int status,Long video, int age, String cast_image_url, String description) {
		Cast cast = new Cast();
		cast.setName(name);
		cast.setStatus(status);
		cast.setVideo(new Video(video));		
		cast.setAge(age);
		cast.setCast_image_url(cast_image_url);
		cast.setDescription(description);
		castRepository.save(cast);

		return cast;
	}
	
	public Cast updateCast(Long id, String name, int status,int video, int age, String cast_image_url, String description) throws EntityNotFoundException {
		Optional<Cast> optCast = castRepository.findById(id);

		if (optCast.isPresent()) {
			Cast cast = optCast.get();

			if (name != null)
				cast.setName(name);
			
			if (video != 0)
				cast.setVideo(new Video((long) video));		
			
			if (age != 0)
				cast.setAge(age);
			
			if (cast_image_url != null)
				cast.setCast_image_url(cast_image_url);
			
			if (description != null)
				cast.setDescription(description);

			castRepository.save(cast);
			return cast;
		}

		throw new EntityNotFoundException("Not found Cast to update!");
	}

	public Cast softDeleteCastById(Long id) throws EntityNotFoundException {
		Optional<Cast> optCast = castRepository.findById(id);

		if (optCast.isPresent()) {
			Cast cast = optCast.get();
			cast.setStatus(0);

			castRepository.save(cast);
			return cast;
		}

		throw new EntityNotFoundException("Not found Cast to update!");
	}

	public Cast setActiveCastById(Long id) throws EntityNotFoundException {
		Optional<Cast> optCast = castRepository.findById(id);

		if (optCast.isPresent()) {
			Cast cast = optCast.get();
			cast.setStatus(1);

			castRepository.save(cast);
			return cast;
		}

		throw new EntityNotFoundException("Not found Cast to update!");
	}

}
