package app.evenements.resolver;

import java.util.Date;
import java.util.Optional;

//import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import app.evenements.model.Author;
import app.evenements.model.Country;
import app.evenements.model.Tutorial;
import app.evenements.model.User;
import app.evenements.repository.AuthorRepository;
import app.evenements.repository.CountryRepository;
import app.evenements.repository.TutorialRepository;
import app.evenements.repository.UserRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;


@Component
public class Mutation implements GraphQLMutationResolver {
	private AuthorRepository authorRepository;
	private TutorialRepository tutorialRepository;
	private UserRepository userRepository;
	private CountryRepository countryRepository;

	public Mutation(AuthorRepository authorRepository, TutorialRepository tutorialRepository,UserRepository userRepository, CountryRepository countryRepository) {
		this.authorRepository = authorRepository;
		this.tutorialRepository = tutorialRepository;
		this.userRepository = userRepository;
		this.countryRepository = countryRepository;
	}

//	@Secured("ROLE_USER")
	public User createUser(String username, String email, String password, String telephone) {
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		user.setPassword(password);
		user.setStatus(0);
		user.setTelephone(telephone);
		user.setCreated_at(new Date().toInstant());
		user.setUpdated_at(new Date().toInstant());

		userRepository.save(user);

		return user;
	}
	
	public Author createAuthor(String name, String email, String telephone,Integer status, Integer age) {
		Author author = new Author();
		author.setName(name); 
		author.setAge(age);
		author.setEmail(email);
		author.setTelephone(telephone);
		author.setStatus(status);

		authorRepository.save(author);

		return author;
	}
	
	public Author softDeleteAuthorById(Long id) throws EntityNotFoundException {
		Optional<Author> optAuthor = authorRepository.findById(id);

		if (optAuthor.isPresent()) {
			Author author = optAuthor.get();
			author.setStatus(0);
			
			authorRepository.save(author);
			return author;
		}

		throw new EntityNotFoundException("Not found Author to update!");
	}

	
	public Country createCountry(String name, Integer status) {
		Country country = new Country();
		country.setName(name);
		country.setStatus(status);

		countryRepository.save(country);

		return country;
	}

	public Tutorial createTutorial(String title, String description, Long authorId) {
		Tutorial book = new Tutorial();
		book.setAuthor(new Author(authorId));
		book.setTitle(title);
		book.setDescription(description);

		tutorialRepository.save(book);

		return book;
	}

	public boolean deleteTutorial(Long id) {
		tutorialRepository.deleteById(id);
		return true;
	}
	
//	public User updateUser(Long id, String telephone, Instant updatedAt) throws EntityNotFoundException {
//		Optional<User> optUser = userRepository.findById(id);
//
//		if (optUser.isPresent()) {
//			User user = optUser.get();
//
//			if (telephone != null)
//				user.setTelephone(telephone);
//			
//			if (updatedAt != null)
//				user.setUpdated_at(updatedAt);
//
//			userRepository.save(user);
//			return user;
//		}
//
//		throw new EntityNotFoundException("Not found Tutorial to update!");
//	}

	public Tutorial updateTutorial(Long id, String title, String description) throws EntityNotFoundException {
		Optional<Tutorial> optTutorial = tutorialRepository.findById(id);

		if (optTutorial.isPresent()) {
			Tutorial tutorial = optTutorial.get();

			if (title != null)
				tutorial.setTitle(title);
			if (description != null)
				tutorial.setDescription(description);

			tutorialRepository.save(tutorial);
			return tutorial;
		}

		throw new EntityNotFoundException("Not found Tutorial to update!");
	}
}
