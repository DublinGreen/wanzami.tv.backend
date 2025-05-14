package tv.wanzami.model;

import java.time.Instant;

import jakarta.persistence.*;

@Entity
public class VideoHistory {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@Column(name = "finished_film", nullable = false, columnDefinition = "int(11) not null default 0")
	private Integer finished_film;
		
	@ManyToOne
	@JoinColumn(name = "video_id", nullable = false, updatable = false)
	private Video video;
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false, updatable = false)
	private User user;
	
	@Column(name = "rating", nullable = false, columnDefinition = "int(11) not null default 0")
	private Integer rating;

	@Column(name = "film_duration_watched", nullable = true)
	private String film_duration_watched;

	@Column(name = "created_at", nullable = true)
	private Instant created_at;
	
	@Column(name = "updated_at", nullable = true)
	private Instant updated_at;

	public VideoHistory() {
	}
	
	public VideoHistory(Long id) {
		this.id = id;
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

	public Integer getFinished_film() {
		return finished_film;
	}

	public void setFinished_film(Integer finished_film) {
		this.finished_film = finished_film;
	}

	public Video getVideo() {
		return video;
	}

	public void setVideo(Video video) {
		this.video = video;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Integer getRating() {
		return rating;
	}

	public void setRating(Integer rating) {
		this.rating = rating;
	}

	public String getFilm_duration_watched() {
		return film_duration_watched;
	}

	public void setFilm_duration_watched(String film_duration_watched) {
		this.film_duration_watched = film_duration_watched;
	}
}
