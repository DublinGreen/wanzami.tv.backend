package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.config.PasswordEncoder;
import tv.wanzami.model.User;
import tv.wanzami.repository.UserRepository;

@Component
public class UserMutation implements GraphQLMutationResolver {

	private UserRepository userRespository;

	public UserMutation(UserRepository userRespository) {
		this.userRespository = userRespository;
	}

	public User createUser(String username, String email, String password, String telephone) {
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		
		PasswordEncoder passwordEncoder = new PasswordEncoder(password);
		user.setPassword(passwordEncoder.encode());
		
		user.setStatus(0);
		user.setTelephone(telephone);
		user.setCreated_at(new Date().toInstant());
		user.setUpdated_at(new Date().toInstant());

		userRespository.save(user);

		return user;
	}

	public User updateUser(Long id, String username, String email, String password, String telephone) throws EntityNotFoundException {
		Optional<User> optUser = userRespository.findById(id);

		if (optUser.isPresent()) {
			User user = optUser.get();

			if (username != null)
				user.setUsername(username);
			
			if (email != null)
				user.setEmail(email);
			
			if (password != null) {
				PasswordEncoder passwordEncoder = new PasswordEncoder(password);
				user.setPassword(passwordEncoder.encode());	
			}
			
			if (telephone != null)
				user.setTelephone(telephone);

			userRespository.save(user);
			return user;
		}

		throw new EntityNotFoundException("Not found User to update!");
	}
	
	public User softDeleteUserById(Long id) throws EntityNotFoundException {
		Optional<User> optUser = userRespository.findById(id);

		if (optUser.isPresent()) {
			User user = optUser.get();
			user.setStatus(0);

			userRespository.save(user);
			return user;
		}

		throw new EntityNotFoundException("Not found User to update!");
	}

	public User setActiveUserById(Long id) throws EntityNotFoundException {
		Optional<User> optUser = userRespository.findById(id);

		if (optUser.isPresent()) {
			User user = optUser.get();
			user.setStatus(1);

			userRespository.save(user);
			return user;
		}

		throw new EntityNotFoundException("Not found User to update!");
	}

}
