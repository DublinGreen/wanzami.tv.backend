package app.evenements.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import app.evenements.model.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {

}