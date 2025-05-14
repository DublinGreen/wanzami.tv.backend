package tv.wanzami.query;

import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.VideoHistory;
import tv.wanzami.repository.VideoHistoryRepository;

@Component
public class VideoHistoryQuery implements GraphQLQueryResolver {

	private VideoHistoryRepository respository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public VideoHistoryQuery(VideoHistoryRepository respository) {
		this.respository = respository;
	}

	public Iterable<VideoHistory> findAllVideoHistories() {
		return respository.findAll();
	}

	public long countVideoHistory() {
		return respository.count();
	}

	public Optional<VideoHistory> videoHistoryById(Long id) {
		return respository.findById(id);
	}
	
}
