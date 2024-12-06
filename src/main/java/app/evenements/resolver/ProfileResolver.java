package app.evenements.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import app.evenements.model.Profile;
import app.evenements.model.User;
import app.evenements.repository.UserRepository;
import graphql.kickstart.tools.GraphQLResolver;

@Component
public class ProfileResolver implements GraphQLResolver<Profile> {
	
	@Autowired
	private UserRepository userRepository;

	public ProfileResolver(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public User getUser(Profile profile) {
		return userRepository.findById(profile.getUser().getId()).orElseThrow(null);
	}
}
