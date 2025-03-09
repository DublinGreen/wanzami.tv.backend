package tv.wanzami.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import tv.wanzami.model.Permission;
import java.util.Optional;

/**
 * Permission Repository Interface
 */
public interface PermissionRepository extends JpaRepository<Permission, Long> {

	Optional<Permission> findByName(String name);
}