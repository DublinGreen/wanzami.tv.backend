package tv.wanzami.model;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
public class EmailConfirmation {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "status", nullable = false, columnDefinition = "int(11) not null default 0")
	private Integer status;

	@Column(name = "user_id", nullable = false)
	private Integer user_id;
	
	@Column(name = "code", nullable = false, unique = true, length = 200)
	private String code;
	
	@Column(name = "created_at", nullable = true)
	private Instant created_at;
	
	@Column(name = "updated_at", nullable = true)
	private Instant updated_at;

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
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
	
}
