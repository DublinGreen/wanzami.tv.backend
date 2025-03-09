package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.Author;
import tv.wanzami.repository.AuthorRepository;

@Component
@CrossOrigin(origins = "http://localhost:3000")
public class AuthorMutation implements GraphQLMutationResolver {

	private AuthorRepository authorRepository;

	public AuthorMutation(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	public Author createAuthor(String name, String email,Integer status, Integer age, String telephone) {
		Author author = new Author();
		author.setName(name); 
		author.setAge(age);
		author.setEmail(email);
		author.setTelephone(telephone);
		author.setStatus(status);
		author.setCreated_at(new Date().toInstant());

		authorRepository.save(author);

		return author;
	}

	public Author updateAuthor(Long id, String name, String email, int age, String telephone) throws EntityNotFoundException {
		Optional<Author> optAuthor = authorRepository.findById(id);

		if (optAuthor.isPresent()) {
			Author author = optAuthor.get();

			if (name != null)
				author.setName(name);
			
			if (email != null)
				author.setEmail(email);
			
			if (telephone != null)
				author.setTelephone(telephone);
			
			if (age != 0)
				author.setAge(age);
			
			author.setUpdated_at(new Date().toInstant());
			authorRepository.save(author);
			return author;
		}

		throw new EntityNotFoundException("Not found Tutorial to update!");
	}
	
	public Author softDeleteAuthorById(Long id) throws EntityNotFoundException {
		Optional<Author> optAuthor = authorRepository.findById(id);

		if (optAuthor.isPresent()) {
			Author author = optAuthor.get();
			author.setStatus(0);
			author.setUpdated_at(new Date().toInstant());

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
			author.setUpdated_at(new Date().toInstant());

			authorRepository.save(author);
			return author;
		}

		throw new EntityNotFoundException("Not found Author to update!");
	}

}
