package tv.wanzami.query;

import java.util.Optional;
import org.springframework.stereotype.Component;
import graphql.kickstart.tools.GraphQLQueryResolver;
import graphql.scalars.ExtendedScalars;
import graphql.schema.GraphQLScalarType;
import tv.wanzami.model.Staff;
import tv.wanzami.repository.StaffRepository;


@Component
public class StaffQuery implements GraphQLQueryResolver {

	private StaffRepository repository;

	GraphQLScalarType longScalar = ExtendedScalars.newAliasedScalar("Long").aliasedScalar(ExtendedScalars.GraphQLLong)
			.build();

	public StaffQuery(StaffRepository repository) {
		this.repository = repository;
	}

	public Iterable<Staff> findAllStaffs() {
		return repository.findAll();
	}

	public long countStaffs() {
		return repository.count();
	}

	public Optional<Staff> staffById(Long id) {
		return repository.findById(id);
	}
	
}
