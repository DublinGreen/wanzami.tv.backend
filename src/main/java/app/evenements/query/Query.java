package app.evenements.query;

import java.util.Optional;

//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import app.evenements.model.Tutorial;
import app.evenements.repository.TutorialRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;

@Component
public class Query implements GraphQLQueryResolver {
	private TutorialRepository tutorialRepository;
	
	GraphQLScalarType longScalar =
      ExtendedScalars.newAliasedScalar("Long")
          .aliasedScalar(ExtendedScalars.GraphQLLong)
          .build();

	public Query(
			TutorialRepository tutorialRepository
			) 
	{
		this.tutorialRepository = tutorialRepository;
	}

//	public Iterable<Tutorial> findAllTutorials() {
//		return tutorialRepository.findAll();
//	}
	
//	public long countTutorials() {
//		return tutorialRepository.count();
//	}
		
	public Optional<Tutorial> tutorialById(Long id) {
		return tutorialRepository.findById(id);
	}

}
