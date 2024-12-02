package app.evenements.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Video_meta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id;
	
	@Column(nullable = false)
	private Long video_id;
	
	@Column(name = "status", nullable = false, columnDefinition = "int(11) not null default 0")
	private Integer status;

	@Column(name = "name", nullable = false, unique = true, length = 200)
	private String name;
	
	@Column(nullable = false, unique = true, length = 200)
	private String video_url;
	
	@Column(nullable = false, unique = true, length = 200)
	private String video_backup_url;
	
	@Column(nullable = false, unique = true, length = 200)
	private String video_into_short_url;
	
	@Column(nullable = false, unique = true, length = 200)
	private String video_into_short_backup_url;
	
	@Column(name = "created_at", nullable = true)
	private Instant created_at;
	
	@Column(name = "updated_at", nullable = true)
	private Instant updated_at;
	
	public Video_meta() {
	}

	public Video_meta(Long id) {
		this.id = id;
	}

	public Video_meta(String name, Integer status) {
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
	
}