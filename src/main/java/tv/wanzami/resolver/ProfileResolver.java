package tv.wanzami.resolver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLResolver;
import tv.wanzami.model.Profile;
import tv.wanzami.model.User;
import tv.wanzami.repository.UserRepository;

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
