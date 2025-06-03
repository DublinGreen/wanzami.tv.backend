package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.Permission;
import tv.wanzami.repository.PermissionRepository;

/**
 * Permisson Mutation
 */
@Component
public class PermissonMutation implements GraphQLMutationResolver {

	private PermissionRepository permissionRepository;

	public PermissonMutation(PermissionRepository permissionRepository) {
		this.permissionRepository = permissionRepository;
	}

	public Permission createPermission(String name, String userType, int status) {
		Permission permission = new Permission();
		permission.setName(name);
		permission.setUserType(userType.toUpperCase());
		permission.setStatus(status);
		permission.setCreated_at(new Date().toInstant());
		
		permissionRepository.save(permission);

		return permission;
	}

	public Permission updatePermission(Long id, String name, String userType) throws EntityNotFoundException {
		Optional<Permission> optPermission = permissionRepository.findById(id);

		if (optPermission.isPresent()) {
			Permission permission = optPermission.get();

			if (name != null)
				permission.setName(name);
			
			if (userType != null)
				permission.setUserType(userType.toUpperCase());
			
			permission.setUpdated_at(new Date().toInstant());

			permissionRepository.save(permission);
			return permission;
		}

		throw new EntityNotFoundException("Not found Permission to update!");
	}
	
	public Permission softDeletePermissionById(Long id) throws EntityNotFoundException {
		Optional<Permission> optPermission = permissionRepository.findById(id);

		if (optPermission.isPresent()) {
			Permission permission = optPermission.get();
			permission.setStatus(0);

			permissionRepository.save(permission);
			return permission;
		}

		throw new EntityNotFoundException("Not found Permission to update!");
	}

	public Permission setActivePermissionById(Long id) throws EntityNotFoundException {
		Optional<Permission> optPermission = permissionRepository.findById(id);

		if (optPermission.isPresent()) {
			Permission permission = optPermission.get();
			permission.setStatus(1);

			permissionRepository.save(permission);
			return permission;
		}

		throw new EntityNotFoundException("Not found Permission to update!");
	}

}
