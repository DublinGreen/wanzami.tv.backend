package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.Staff;
import tv.wanzami.model.Video;
import tv.wanzami.repository.StaffRepository;

@Component
public class StaffMutation implements GraphQLMutationResolver {

	private StaffRepository repository;

	public StaffMutation(StaffRepository repository) {
		this.repository = repository;
	}
	
	public Staff createStaff(String name, String title, Long video_id) {
		Staff staff = new Staff();
		staff.setName(name);
		staff.setStatus(0);		
		staff.setTitle(title);
		staff.setVideo(new Video(video_id));
		staff.setCreated_at(new Date().toInstant());
		repository.save(staff);

		return staff;
	}
	
	public Staff updateStaff(Long id, String name, String title, Long video_id) throws EntityNotFoundException {
		Optional<Staff> optStaff = repository.findById(id);

		if (optStaff.isPresent()) {
			Staff staff = optStaff.get();

			if (name != null)
				staff.setName(name);
			
			if (title != null)
				staff.setTitle(title);
			
			if (video_id != null)
				staff.setVideo(new Video(video_id));
			
			staff.setUpdated_at(new Date().toInstant());

			repository.save(staff);
			return staff;
		}

		throw new EntityNotFoundException("Not found Cast to update!");
	}

	public Staff softDeleteStaffById(Long id) throws EntityNotFoundException {
		Optional<Staff> optStaff = repository.findById(id);

		if (optStaff.isPresent()) {
			Staff staff = optStaff.get();
			staff.setStatus(0);
			staff.setUpdated_at(new Date().toInstant());

			repository.save(staff);
			return staff;
		}

		throw new EntityNotFoundException("Not found Staff to update!");
	}

	public Staff setActiveStaffById(Long id) throws EntityNotFoundException {
		Optional<Staff> optStaff = repository.findById(id);

		if (optStaff.isPresent()) {
			Staff staff = optStaff.get();
			staff.setStatus(1);
			staff.setUpdated_at(new Date().toInstant());

			repository.save(staff);
			return staff;
		}

		throw new EntityNotFoundException("Not found Staff to update!");
	}

}
