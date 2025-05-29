package tv.wanzami.query;

import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.User;
import tv.wanzami.model.VideoWishList;
import tv.wanzami.repository.UserRepository;
import tv.wanzami.repository.VideoWishListRepository;

@Component
public class VideoWishListQuery implements GraphQLQueryResolver {

	private VideoWishListRepository respository;
	private UserRepository userRepository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public VideoWishListQuery(VideoWishListRepository respository, UserRepository userRepository) {
		this.respository = respository;
		this.userRepository = userRepository;
	}

	public Iterable<VideoWishList> findAllWishLists() {
		return respository.findAll();
	}

	public long countWishList() {
		return respository.count();
	}

	public Optional<VideoWishList> wishListById(Long id) {
		return respository.findById(id);
	}
	
	public Iterable<VideoWishList> wishListByUserEmail(String email) {	
		Optional<User> user = userRepository.findByEmail(email);
		Iterable<VideoWishList> returnValue = null;
		
		if(user.isPresent()) {
			returnValue = respository.findByUserId(user.get().getId());
		}
		
		return returnValue;
	}
	
}
