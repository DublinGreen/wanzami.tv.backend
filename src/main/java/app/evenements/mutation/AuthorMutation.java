package app.evenements.mutation;

import java.util.Optional;

import org.springframework.stereotype.Component;

import app.evenements.model.Author;
import app.evenements.repository.AuthorRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;

@Component
public class AuthorMutation implements GraphQLMutationResolver {

	private AuthorRepository authorRepository;

	public AuthorMutation(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
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

	public Author setActiveAuthorById(Long id) throws EntityNotFoundException {
		Optional<Author> optAuthor = authorRepository.findById(id);

		if (optAuthor.isPresent()) {
			Author author = optAuthor.get();
			author.setStatus(1);

			authorRepository.save(author);
			return author;
		}

		throw new EntityNotFoundException("Not found Author to update!");
	}

}
