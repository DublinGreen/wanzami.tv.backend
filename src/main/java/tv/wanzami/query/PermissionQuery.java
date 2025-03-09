package tv.wanzami.query;

import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.Permission;
import tv.wanzami.repository.PermissionRepository;

/**
 * Permission Query
 */
@Component
@CrossOrigin(origins = "http://localhost:3000")
public class PermissionQuery implements GraphQLQueryResolver {

	private PermissionRepository permissionRepository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public PermissionQuery(PermissionRepository permissionRepository) {
		this.permissionRepository = permissionRepository;
	}

	public Iterable<Permission> findAllPermission() {
		return permissionRepository.findAll();
	}

	public long countPermission() {
		return permissionRepository.count();
	}

	public Optional<Permission> permissionById(Long id) {
		return permissionRepository.findById(id);
	}
		
	public Optional<Permission> permissionByName(String name) {
		return permissionRepository.findByName(name);
	}
}
