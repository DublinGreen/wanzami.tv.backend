package app.evenements.query;

import java.util.Optional;

import org.springframework.stereotype.Component;

import app.evenements.model.Author;
import app.evenements.repository.AuthorRepository;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;

@Component
public class AuthorQuery implements GraphQLQueryResolver {

	private AuthorRepository authorRepository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public AuthorQuery(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	public Iterable<Author> findAllAuthors() {
		return authorRepository.findAll();
	}

	public long countAuthors() {
		return authorRepository.count();
	}

	public Optional<Author> authorById(Long id) {
		return authorRepository.findById(id);
	}
}
