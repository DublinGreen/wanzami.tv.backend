package app.evenements.query;

import java.util.Optional;

//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import app.evenements.model.Tutorial;
import app.evenements.model.User;
import app.evenements.repository.TutorialRepository;
import app.evenements.repository.UserRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;

@Component
public class Query implements GraphQLQueryResolver {
	private TutorialRepository tutorialRepository;
	private UserRepository userRepository;
	
	GraphQLScalarType longScalar =
      ExtendedScalars.newAliasedScalar("Long")
          .aliasedScalar(ExtendedScalars.GraphQLLong)
          .build();

	public Query(
			TutorialRepository tutorialRepository, 
			UserRepository userRepository
			) 
	{
		this.tutorialRepository = tutorialRepository;
		this.userRepository = userRepository;
	}

//	@Secured("ROLE_ADMIN")
//	@PreAuthorize("hasRole('ADMIN')")
	public Iterable<User> findAllUsers() {
		return userRepository.findAll();
	}
	
//	public Iterable<Tutorial> findAllTutorials() {
//		return tutorialRepository.findAll();
//	}

	public long countUsers() {
		return userRepository.count();
	}
	
//	public long countTutorials() {
//		return tutorialRepository.count();
//	}
		
	public Optional<User> userById(Long id) {
		return userRepository.findById(id);
	}

	public Optional<Tutorial> tutorialById(Long id) {
		return tutorialRepository.findById(id);
	}

}
