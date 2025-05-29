package tv.wanzami.model;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
public class VideoPayment {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
		
	@Column(nullable = false, unique = false)
	private String email;
	
	@Column(name = "videoId", nullable = false)
	private Integer videoId;
	
	@Column(nullable = false, unique = true)
	private String reference;
	
	@Column(nullable = false)
	private String amount;
	
	@Column(nullable = false)
	private String currency;
	
	@Column(nullable = false)
	private String transaction_status;
	
	@Column(nullable = false)
	private String channel;

	@Column(name = "created_at", nullable = true)
	private Instant created_at;
	
	@Column(name = "updated_at", nullable = true)
	private Instant updated_at;
	
	public VideoPayment() {
	}

	public VideoPayment(Long id) {
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

	public String getReference() {
		return reference;
	}

	public void setReference(String reference) {
		this.reference = reference;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currrency) {
		this.currency = currrency;
	}

	public Integer getVideoId() {
		return videoId;
	}

	public void setVideoId(Integer videoId) {
		this.videoId = videoId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTransaction_status() {
		return transaction_status;
	}

	public void setTransaction_status(String transaction_status) {
		this.transaction_status = transaction_status;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

}
