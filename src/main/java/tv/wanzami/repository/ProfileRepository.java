package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wanzami.model.Profile;

public interface ProfileRepository extends JpaRepository<Profile, Long> {

}