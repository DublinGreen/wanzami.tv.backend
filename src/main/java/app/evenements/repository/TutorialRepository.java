package app.evenements.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.evenements.model.Tutorial;

public interface TutorialRepository extends JpaRepository<Tutorial, Long> {

}
