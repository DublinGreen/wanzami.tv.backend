package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.enums.Currency;import tv.wanzami.model.Country;
import tv.wanzami.model.Price;
import tv.wanzami.repository.PriceRepository;

@Component
public class PriceMutation implements GraphQLMutationResolver {

	private PriceRepository repository;

	public PriceMutation(PriceRepository repository) {
		this.repository = repository;
	}
	
	public Price createPrice(String currency, String amount, Integer country_id) {
		Price price = new Price();
		price.setPrice(amount);
		price.setCurrency(currency);
		price.setStatus(0);
		price.setCountry(new Country((long) country_id));
		price.setCreated_at(new Date().toInstant());
		repository.save(price);

		return price;
	}
	
	public Price updatePrice(Long id, String currency, String amount, Integer country_id) throws EntityNotFoundException {
		Optional<Price> opt = repository.findById(id);

		if (opt.isPresent()) {
			Price price = opt.get();

			if (amount != null)
				price.setPrice(amount);
			
			if (country_id != null)
				price.setCountry(new Country((long) country_id));

			if (currency != null && (currency.equalsIgnoreCase(Currency.NGN.toString()) || currency.equalsIgnoreCase(Currency.USD.toString()))){
				price.setCurrency(currency);
			}
			
			price.setUpdated_at(new Date().toInstant());

			repository.save(price);
			return price;
		}

		throw new EntityNotFoundException("Not found Price to update!");
	}

	public Price softDeletePriceById(Long id) throws EntityNotFoundException {
		Optional<Price> opt = repository.findById(id);

		if (opt.isPresent()) {
			Price price = opt.get();
			price.setStatus(0);
			price.setUpdated_at(new Date().toInstant());

			repository.save(price);
			return price;
		}

		throw new EntityNotFoundException("Not found Price to update!");
	}

	public Price setActivePriceById(Long id) throws EntityNotFoundException {
		Optional<Price> opt = repository.findById(id);

		if (opt.isPresent()) {
			Price price = opt.get();
			price.setStatus(1);
			price.setUpdated_at(new Date().toInstant());

			repository.save(price);
			return price;
		}

		throw new EntityNotFoundException("Not found Price to update!");
	}

}
