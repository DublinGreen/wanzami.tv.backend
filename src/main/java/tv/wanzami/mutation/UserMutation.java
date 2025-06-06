package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.config.PasswordEncoder;
import tv.wanzami.enums.Role;
import tv.wanzami.model.User;
import tv.wanzami.repository.UserRepository;

@Component
public class UserMutation implements GraphQLMutationResolver {

	private UserRepository userRespository;
    
	public UserMutation(UserRepository userRespository) {
		this.userRespository = userRespository;
	}

	public User createUser(String firstName, String lastName, String email, String password, String role) {
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		user.setEmail(email);
		
		PasswordEncoder passwordEncoder = new PasswordEncoder(password);
		user.setPassword(passwordEncoder.encode());
		
		user.setStatus(0);
		user.setCreated_at(new Date().toInstant());

		if (role != null && 
				(role.equalsIgnoreCase(Role.ADMIN.toString()) || role.equalsIgnoreCase(Role.NORMAL.toString()))) {
			user.setRole(role);
		}
		
		userRespository.save(user);

		return user;
	}

	public User updateUser(Long id, String firstName, String lastName, String email, String password) throws EntityNotFoundException {
		Optional<User> optUser = userRespository.findById(id);

		if (optUser.isPresent()) {
			User user = optUser.get();

			if (firstName != null)
				user.setFirstName(firstName);
			
			if (lastName != null)
				user.setLastName(lastName);
			
			if (email != null)
				user.setEmail(email);
			
			if (password != null) {
				PasswordEncoder passwordEncoder = new PasswordEncoder(password);
				user.setPassword(passwordEncoder.encode());	
			}
			
			user.setUpdated_at(new Date().toInstant());

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
			user.setUpdated_at(new Date().toInstant());
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
			user.setUpdated_at(new Date().toInstant());
			userRespository.save(user);
			return user;
		}

		throw new EntityNotFoundException("Not found User to update!");
	}
	
}
