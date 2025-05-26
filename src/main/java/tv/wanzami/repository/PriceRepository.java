package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wanzami.model.Price;

public interface PriceRepository extends JpaRepository<Price, Long> {
}