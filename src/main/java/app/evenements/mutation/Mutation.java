package app.evenements.mutation;

import java.util.Optional;

//import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;

import app.evenements.model.Author;
import app.evenements.model.Tutorial;
import app.evenements.repository.TutorialRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;

@Component
public class Mutation implements GraphQLMutationResolver {
	private TutorialRepository tutorialRepository;

	public Mutation(TutorialRepository tutorialRepository) {
		this.tutorialRepository = tutorialRepository;
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
