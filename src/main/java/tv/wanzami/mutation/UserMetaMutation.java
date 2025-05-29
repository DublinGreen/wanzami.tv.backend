package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.UserMeta;
import tv.wanzami.repository.UserMetaRepository;

@Component
public class UserMetaMutation implements GraphQLMutationResolver {

	private UserMetaRepository repository;
    
	public UserMetaMutation(UserMetaRepository repository) {
		this.repository = repository;
	}

	public UserMeta createOrUpdateUserMeta(String email, String gender, String day, String month) throws EntityNotFoundException {
		UserMeta userMeta;

		Optional<UserMeta> optUserMeta = repository.findByEmail(email);
		
		if(optUserMeta.isPresent()) {
			System.out.println("optUserMeta isPresent");
			userMeta = optUserMeta.get();
			
			userMeta.setEmail(email);
	
			if (gender != null)
				userMeta.setGender(gender);
			
			if (day != null)
				userMeta.setDayOfBirth(day);
			
			if (month != null)
				userMeta.setMonthOfBirth(month);
			
			userMeta.setUpdated_at(new Date().toInstant());

			repository.save(userMeta);
			
			return userMeta;

		}else {
			System.out.println("optUserMeta not isPresent");
			userMeta = new UserMeta();
			
			userMeta.setEmail(email);
			userMeta.setGender(gender);
			userMeta.setDayOfBirth(day);
			userMeta.setMonthOfBirth(month);
			userMeta.setCreated_at(new Date().toInstant());
			
			repository.save(userMeta);
			
			return userMeta;
		}
		
	}
	
}
