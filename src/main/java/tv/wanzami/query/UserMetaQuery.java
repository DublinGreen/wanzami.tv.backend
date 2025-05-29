package tv.wanzami.query;

import java.util.Optional;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.UserMeta;
import tv.wanzami.repository.UserMetaRepository;

@Component
public class UserMetaQuery implements GraphQLQueryResolver {

	private UserMetaRepository repository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public UserMetaQuery(UserMetaRepository repository) {
		this.repository = repository;
	}

	public Iterable<UserMeta> findAllUsersMeta() {
	    Sort sort = Sort.by(Sort.Direction.fromString("desc"), "id");
		return repository.findAll(sort);
	}
	
	public long countUsersMeta() {
		return repository.count();
	}

	public Optional<UserMeta> userMetaById(Long id) {
		return repository.findById(id);
	}
	
	public Optional<UserMeta> userMetaByEmail(String email) {
		return repository.findByEmail(email);
	}
}
