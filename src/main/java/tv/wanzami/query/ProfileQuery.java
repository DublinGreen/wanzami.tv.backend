package tv.wanzami.query;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.Profile;
import tv.wanzami.repository.ProfileRepository;

@Component
public class ProfileQuery implements GraphQLQueryResolver {

	private ProfileRepository profileRepository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public ProfileQuery(ProfileRepository profileRepository) {
		this.profileRepository = profileRepository;
	}

	public Iterable<Profile> findAllProfiles() {
		return profileRepository.findAll();
	}

	public long countProfiles() {
		return profileRepository.count();
	}

	public Optional<Profile> profileById(Long id) {
		return profileRepository.findById(id);
	}

}
