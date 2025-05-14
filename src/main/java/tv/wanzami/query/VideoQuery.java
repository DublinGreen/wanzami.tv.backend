package tv.wanzami.query;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.Video;
import tv.wanzami.repository.VideoRepository;

@Component
@CrossOrigin(origins = "http://localhost:3000")
public class VideoQuery implements GraphQLQueryResolver {

	private VideoRepository videoRepository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public VideoQuery(VideoRepository videoRepository) {
		this.videoRepository = videoRepository;
	}

	public Iterable<Video> findAllVideos() {
		return videoRepository.findAll();
	}

	public long countVideos() {
		return videoRepository.count();
	}

	public Optional<Video> videoById(Long id) {
		return videoRepository.findById(id);
	}

}
