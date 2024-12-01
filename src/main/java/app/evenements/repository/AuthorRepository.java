package app.evenements.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.evenements.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Long> {

}