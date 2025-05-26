package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.Price;
import tv.wanzami.model.VideoPrice;
import tv.wanzami.repository.VideoPriceRepository;

@Component
public class VideoPriceMutation implements GraphQLMutationResolver {

	private VideoPriceRepository repository;

	public VideoPriceMutation(VideoPriceRepository repository) {
		this.repository = repository;
	}
	
	public VideoPrice createVideoPrice(Integer video_id, Long price_id) {
		VideoPrice videoPrice = new VideoPrice();
		videoPrice.setStatus(0);		
		videoPrice.setVideo_id(video_id);
		videoPrice.setPrice(new Price(price_id));
		videoPrice.setCreated_at(new Date().toInstant());
		repository.save(videoPrice);

		return videoPrice;
	}
	
	public VideoPrice updateVideoPrice(Long id, Integer video_id, Long price_id) throws EntityNotFoundException {
		Optional<VideoPrice> opt = repository.findById(id);

		if (opt.isPresent()) {
			VideoPrice videoPrice = opt.get();

			if (video_id != null)
				videoPrice.setVideo_id(video_id);
			
			if (price_id != null)
				videoPrice.setPrice((new Price(price_id)));

			
			videoPrice.setUpdated_at(new Date().toInstant());

			repository.save(videoPrice);
			return videoPrice;
		}

		throw new EntityNotFoundException("Not found Video Cast to update!");
	}

	public VideoPrice softDeleteVideoPriceById(Long id) throws EntityNotFoundException {
		Optional<VideoPrice> opt = repository.findById(id);

		if (opt.isPresent()) {
			VideoPrice videoPrice = opt.get();
			videoPrice.setStatus(0);
			videoPrice.setUpdated_at(new Date().toInstant());

			repository.save(videoPrice);
			return videoPrice;
		}

		throw new EntityNotFoundException("Not found Video Price to update!");
	}

	public VideoPrice setActiveVideoPriceById(Long id) throws EntityNotFoundException {
		Optional<VideoPrice> opt = repository.findById(id);

		if (opt.isPresent()) {
			VideoPrice videoPrice = opt.get();
			videoPrice.setStatus(1);
			videoPrice.setUpdated_at(new Date().toInstant());

			repository.save(videoPrice);
			return videoPrice;
		}

		throw new EntityNotFoundException("Not found Video Price to update!");
	}

}
