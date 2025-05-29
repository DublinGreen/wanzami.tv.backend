package tv.wanzami.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import tv.wanzami.enums.Currency;

@Entity
public class Price {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
		
	@Column(name = "status", nullable = false, columnDefinition = "int(11) not null default 0")
	private Integer status;

	@Column(name = "price", nullable = false,unique = false)
	private String price;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "currency", nullable = false)
    private Currency currency;
    
	@ManyToOne
	@JoinColumn(name = "country_id", nullable = true, updatable = false)
	private Country country;
    
	@Column(name = "created_at", nullable = true)
	private Instant created_at;
	
	@Column(name = "updated_at", nullable = true)
	private Instant updated_at;
	
	public Price() {
	}

	public Price(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public Instant getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Instant updated_at) {
		this.updated_at = updated_at;
	}

	public Instant getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Instant created_at) {
		this.created_at = created_at;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Currency getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = Currency.valueOf(currency);
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
