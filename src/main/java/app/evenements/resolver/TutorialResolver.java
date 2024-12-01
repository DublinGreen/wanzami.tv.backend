package app.evenements.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.evenements.model.Author;
import app.evenements.model.Tutorial;
import app.evenements.repository.AuthorRepository;
import graphql.kickstart.tools.GraphQLResolver;

@Component
public class TutorialResolver implements GraphQLResolver<Tutorial> {
	@Autowired
	private AuthorRepository authorRepository;

	public TutorialResolver(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	public Author getAuthor(Tutorial tutorial) {
		return authorRepository.findById(tutorial.getAuthor().getId()).orElseThrow(null);
	}
}
