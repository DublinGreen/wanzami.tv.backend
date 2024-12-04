package app.evenements.query;

import java.util.Optional;

//import org.springframework.security.access.annotation.Secured;
//import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import app.evenements.model.Author;
import app.evenements.model.Country;
import app.evenements.model.Tutorial;
import app.evenements.model.User;
import app.evenements.repository.AuthorRepository;
import app.evenements.repository.CountryRepository;
import app.evenements.repository.TutorialRepository;
import app.evenements.repository.UserRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;

@Component
public class Query implements GraphQLQueryResolver {
	private AuthorRepository authorRepository;
	private TutorialRepository tutorialRepository;
	private UserRepository userRepository;
	private CountryRepository countryRepository;
	
	GraphQLScalarType longScalar =
      ExtendedScalars.newAliasedScalar("Long")
          .aliasedScalar(ExtendedScalars.GraphQLLong)
          .build();

	public Query(
			AuthorRepository authorRepository, 
			TutorialRepository tutorialRepository, 
			UserRepository userRepository, 
			CountryRepository countryRepository	
			) 
	{
		this.authorRepository = authorRepository;
		this.tutorialRepository = tutorialRepository;
		this.userRepository = userRepository;
		this.countryRepository = countryRepository;
	}

//	@Secured("ROLE_ADMIN")
//	@PreAuthorize("hasRole('ADMIN')")
	public Iterable<User> findAllUsers() {
		return userRepository.findAll();
	}
	
	public Iterable<Country> findAllCountries() {
		return countryRepository.findAll();
	}
	
	
	public Iterable<Author> findAllAuthors() {
		return authorRepository.findAll();
	}
	

	
//	public Iterable<Tutorial> findAllTutorials() {
//		return tutorialRepository.findAll();
//	}

	public long countUsers() {
		return userRepository.count();
	}
	
	public long countAuthors() {
		return authorRepository.count();
	}

//	public long countTutorials() {
//		return tutorialRepository.count();
//	}
	
	public long countCountries() {
		return countryRepository.count();
	}
	
	public Optional<User> userById(Long id) {
		return userRepository.findById(id);
	}
	
	public Optional<Country> countryById(Long id) {
		return countryRepository.findById(id);
	}
	
	public Optional<Author> authorById(Long id) {
		return authorRepository.findById(id);
	}
	
	public Optional<Tutorial> tutorialById(Long id) {
		return tutorialRepository.findById(id);
	}

}
