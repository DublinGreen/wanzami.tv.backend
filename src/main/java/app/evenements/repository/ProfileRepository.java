package app.evenements.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.evenements.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}