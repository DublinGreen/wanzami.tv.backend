package tv.wanzami.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Permission {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id;

	@Column(name = "status", nullable = false, columnDefinition = "int(11) not null default 0")
	private Integer status;

	@Column(name = "name", nullable = false, unique = true, length = 200)
	private String name;

	@Column(name = "user_type", nullable = true)
	private String userType;

	@Column(name = "created_at", nullable = true)
	private Instant created_at;

	@Column(name = "updated_at", nullable = true)
	private Instant updated_at;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Instant getCreated_at() {
		return created_at;
	}

	public void setCreated_at(Instant created_at) {
		this.created_at = created_at;
	}

	public Instant getUpdated_at() {
		return updated_at;
	}

	public void setUpdated_at(Instant updated_at) {
		this.updated_at = updated_at;
	}
}
