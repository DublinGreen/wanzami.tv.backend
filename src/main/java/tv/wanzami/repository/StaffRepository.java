package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import tv.wanzami.model.Staff;

public interface StaffRepository extends JpaRepository<Staff, Long> {
}