package app.evenements.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.evenements.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}