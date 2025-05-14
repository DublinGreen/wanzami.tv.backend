package tv.wanzami.query;

import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.WishList;
import tv.wanzami.repository.WishListRepository;

@Component
public class WishListQuery implements GraphQLQueryResolver {

	private WishListRepository respository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public WishListQuery(WishListRepository respository) {
		this.respository = respository;
	}

	public Iterable<WishList> findAllWishLists() {
		return respository.findAll();
	}

	public long countWishList() {
		return respository.count();
	}

	public Optional<WishList> wishListById(Long id) {
		return respository.findById(id);
	}
	
}
