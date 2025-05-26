package tv.wanzami.query;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.VideoPayment;
import tv.wanzami.repository.VideoPaymentRepository;

@Component
public class VideoPaymentQuery implements GraphQLQueryResolver {

	private VideoPaymentRepository repository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public VideoPaymentQuery(VideoPaymentRepository repository) {
		this.repository = repository;
	}

	public Iterable<VideoPayment> findAllVideoPayment() {
		return repository.findAll();
	}
	
	public long countVideoPayments() {
		return repository.count();
	}

	public Optional<VideoPayment> videoPaymentById(Long id) {
		return repository.findById(id);
	}
	
	public List<VideoPayment> videoPaymentByVideoId(Long videoId) {
		return repository.findVideoPaymentByVideoId(videoId);
	}
	
	public List<VideoPayment> videoPaymentByEmail(String email) {
		return repository.findVideoPaymentByEmail(email);
	}
	
}
