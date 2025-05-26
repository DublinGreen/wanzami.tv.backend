package tv.wanzami.query;

import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.Price;
import tv.wanzami.repository.PriceRepository;

@Component
public class PriceQuery implements GraphQLQueryResolver {

	private PriceRepository repository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public PriceQuery(PriceRepository repository) {
		this.repository = repository;
	}

	public Iterable<Price> findAllPrices() {
		return repository.findAll();
	}

	public long countPrice() {
		return repository.count();
	}

	public Optional<Price> priceById(Long id) {
		return repository.findById(id);
	}
	
}
