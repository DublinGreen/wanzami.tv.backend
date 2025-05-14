package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;
import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.Cast;
import tv.wanzami.repository.CastRepository;

@Component
public class CastMutation implements GraphQLMutationResolver {

	private CastRepository castRepository;

	public CastMutation(CastRepository castRepository) {
		this.castRepository = castRepository;
	}
	
	public Cast createCast(String name, String cast_image_url) {
		Cast cast = new Cast();
		cast.setName(name);
		cast.setStatus(0);		
		cast.setCast_image_url(cast_image_url);
		cast.setCreated_at(new Date().toInstant());
		castRepository.save(cast);

		return cast;
	}
	
	public Cast updateCast(Long id, String name, String cast_image_url) throws EntityNotFoundException {
		Optional<Cast> optCast = castRepository.findById(id);

		if (optCast.isPresent()) {
			Cast cast = optCast.get();

			if (name != null)
				cast.setName(name);
			
			cast.setUpdated_at(new Date().toInstant());

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
			cast.setUpdated_at(new Date().toInstant());

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
			cast.setUpdated_at(new Date().toInstant());

			castRepository.save(cast);
			return cast;
		}

		throw new EntityNotFoundException("Not found Cast to update!");
	}

}
