package tv.wanzami.mutation;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.CrossOrigin;

import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;
import tv.wanzami.model.Category;
import tv.wanzami.repository.CategoryRepository;

@Component
public class CategoryMutation implements GraphQLMutationResolver {

	private CategoryRepository categoryRepository;

	public CategoryMutation(CategoryRepository categoryRepository) {
		this.categoryRepository = categoryRepository;
	}

	public Category createCategory(String name, Integer status) {
		Category category = new Category();
		category.setName(name);
		category.setStatus(status);

		category.setCreated_at(new Date().toInstant());
		categoryRepository.save(category);

		return category;
	}
	
	public Category updateCategory(Long id, String name) throws EntityNotFoundException {
		Optional<Category> optCategory = categoryRepository.findById(id);

		if (optCategory.isPresent()) {
			Category category = optCategory.get();

			if (name != null)
				category.setName(name);
				category.setUpdated_at(new Date().toInstant());

			categoryRepository.save(category);
			return category;
		}

		throw new EntityNotFoundException("Not found Tutorial to update!");
	}

	public Category softDeleteCategoryById(Long id) throws EntityNotFoundException {
		Optional<Category> optCategory = categoryRepository.findById(id);

		if (optCategory.isPresent()) {
			Category category = optCategory.get();
			category.setStatus(0);
			category.setUpdated_at(new Date().toInstant());
			categoryRepository.save(category);
			return category;
		}

		throw new EntityNotFoundException("Not found Author to update!");
	}

	public Category setActiveCategoryById(Long id) throws EntityNotFoundException {
		Optional<Category> optCategory = categoryRepository.findById(id);

		if (optCategory.isPresent()) {
			Category category = optCategory.get();
			category.setStatus(1);
			category.setUpdated_at(new Date().toInstant());
			categoryRepository.save(category);
			return category;
		}

		throw new EntityNotFoundException("Not found Category to update!");
	}

}
