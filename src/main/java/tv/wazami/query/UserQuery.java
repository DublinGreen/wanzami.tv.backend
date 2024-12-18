package tv.wazami.query;

import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wazami.model.User;
import tv.wazami.repository.UserRepository;

@Component
public class UserQuery implements GraphQLQueryResolver {

	private UserRepository userRepository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public UserQuery(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

//	@Secured("ROLE_ADMIN")
//	@PreAuthorize("hasRole('ADMIN')")
	public Iterable<User> findAllUsers() {
		return userRepository.findAll();
	}

	public long countUsers() {
		return userRepository.count();
	}

	public Optional<User> userById(Long id) {
		return userRepository.findById(id);
	}

}
