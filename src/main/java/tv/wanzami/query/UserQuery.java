package tv.wanzami.query;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.User;
import tv.wanzami.repository.UserRepository;
import tv.wanzami.service.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

@Component
@CrossOrigin(origins = "http://localhost:3000")
public class UserQuery implements GraphQLQueryResolver {

	private UserRepository userRepository;
	
    @Autowired
    private EmailService emailService;
    
	private boolean sendTestMail(String to, String subject, String body) throws Exception {
        try {
            emailService.sendEmail(to, subject, body);
            return true;
        }catch (Exception e) {
        	return false;
		}
	}

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public UserQuery(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	public Iterable<User> findAllUsers() {
		try {
//			sendTestMail("greendublin007@gmail.com","testing 123","testing, please ignore");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	    Sort sort = Sort.by(Sort.Direction.fromString("desc"), "id");
		return userRepository.findAll(sort);
	}
	
	public Iterable<User> findAllActiveUsers() {
		return userRepository.findAllActiveUsers();
	}

	public long countUsers() {
		return userRepository.count();
	}

	public Optional<User> userById(Long id) {
		return userRepository.findById(id);
	}

}
