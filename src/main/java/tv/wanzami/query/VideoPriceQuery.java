package tv.wanzami.query;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.VideoPrice;
import tv.wanzami.repository.VideoPriceRepository;

@Component
public class VideoPriceQuery implements GraphQLQueryResolver {

	private VideoPriceRepository repository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public VideoPriceQuery(VideoPriceRepository repository) {
		this.repository = repository;
	}

	public Iterable<VideoPrice> findAllVideoPrices() {
		return repository.findAll();
	}
	
	public long countVideoPrice() {
		return repository.count();
	}

	public Optional<VideoPrice> videoPriceById(Long id) {
		return repository.findById(id);
	}
	
	public List<VideoPrice> videoPriceByVideoId(Long videoId) {
		return repository.findVideoPricesByVideoId(videoId);
	}
	
}
