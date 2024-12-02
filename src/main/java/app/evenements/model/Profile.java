package app.evenements.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Profile {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id;
	
	@Column(nullable = false)
	private Long user_id;
	
	@Column(name = "status", nullable = false, columnDefinition = "int(11) not null default 0")
	private Integer status;

	@Column(name = "name", nullable = false, unique = true, length = 200)
	private String name;
	
	@Column(nullable = false, unique = false, length = 200)
	private String avatar;
	
	@Column(name = "created_at", nullable = true)
	private Instant created_at;
	
	@Column(name = "updated_at", nullable = true)
	private Instant updated_at;
	
	public Profile() {
	}

	public Profile(Long id) {
		this.id = id;
	}

	public Profile(String name, Integer status) {
		this.setName(name);
		this.setStatus(status);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getAvatar() {
		return avatar;
	}

	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}

	public Long getUser_id() {
		return user_id;
	}

	public void setUser_id(Long user_id) {
		this.user_id = user_id;
	}
	
}