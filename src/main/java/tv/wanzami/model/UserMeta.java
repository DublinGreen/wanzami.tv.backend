package tv.wanzami.model;

import java.time.Instant;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import tv.wanzami.enums.Gender;

@Entity
public class UserMeta {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(nullable = false)
	private Long id;
		
	@Column(nullable = false, length = 200)
	private String email;
	
    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;
	
	@Column(nullable = false, length = 200)
	private String monthOfBirth;
		
	@Column(nullable = false, unique = false, length = 200)
	private String dayOfBirth;
	
	@Column(name = "created_at", nullable = true)
	private Instant created_at;
	
	@Column(name = "updated_at", nullable = true)
	private Instant updated_at;
	
	public UserMeta() {
	}

	public UserMeta(Long id) {
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

	public Gender getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = Gender.valueOf(gender);
	}

	public String getMonthOfBirth() {
		return monthOfBirth;
	}

	public void setMonthOfBirth(String monthOfBirth) {
		this.monthOfBirth = monthOfBirth;
	}

	public String getDayOfBirth() {
		return dayOfBirth;
	}

	public void setDayOfBirth(String dayOfBirth) {
		this.dayOfBirth = dayOfBirth;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
}