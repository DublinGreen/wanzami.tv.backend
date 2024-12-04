package app.evenements.mutation;

import java.util.Optional;

import org.springframework.stereotype.Component;

import app.evenements.model.Category;
import app.evenements.repository.CategoryRepository;
import graphql.kickstart.tools.GraphQLMutationResolver;
import jakarta.persistence.EntityNotFoundException;

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

		categoryRepository.save(category);

		return category;
	}

	public Category softDeleteCategoryById(Long id) throws EntityNotFoundException {
		Optional<Category> optCategory = categoryRepository.findById(id);

		if (optCategory.isPresent()) {
			Category category = optCategory.get();
			category.setStatus(0);

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

			categoryRepository.save(category);
			return category;
		}

		throw new EntityNotFoundException("Not found Category to update!");
	}

}
