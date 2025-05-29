package tv.wanzami.mutation;

import java.util.Date;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import tv.wanzami.model.VideoPayment;
import tv.wanzami.repository.VideoPaymentRepository;

@Component
public class VideoPaymentMutation implements GraphQLMutationResolver {

	private VideoPaymentRepository repository;

	public VideoPaymentMutation(VideoPaymentRepository repository) {
		this.repository = repository;
	}
	
	public VideoPayment createVideoPayment(Integer video_id, String email, String reference, String amount, String currency, String transaction_status, String channel) {
		VideoPayment videoPayment = new VideoPayment();
		videoPayment.setVideoId(video_id);
		videoPayment.setEmail(email);
		videoPayment.setReference(reference);
		videoPayment.setAmount(amount);
		videoPayment.setCurrency(currency);
		videoPayment.setChannel(channel);
		videoPayment.setTransaction_status(transaction_status);
		videoPayment.setCreated_at(new Date().toInstant());
		repository.save(videoPayment);

		return videoPayment;
	}
	
}
