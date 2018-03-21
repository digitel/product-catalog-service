package org.tmf.openapi.catalog.service;

import static org.tmf.openapi.catalog.common.ListUtils.toList;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmf.openapi.catalog.domain.catalog.Category;
import org.tmf.openapi.catalog.repository.CategoryRepository;

import com.querydsl.core.types.Predicate;

@Service
public class CategoryService {

	@Autowired
	private CategoryRepository categoryRepository;

	public Category createCategory(@Valid Category category) {

		if (category.getId() != null) {
			throw new IllegalArgumentException("id must be empty while creating Category");
		}

		setDefaultValues(category);

		return categoryRepository.save(category);
	}

	public Category findCategory(@NotNull String id) {
		return categoryRepository.findById(id).get();
	}

	public List<Category> findAllCategories(Predicate predicate) {

		if (null == predicate) {
			return categoryRepository.findAll();
		}
		return toList(categoryRepository.findAll(predicate));
	}

	public void deleteCategory(@NotNull String id) {
		Category existingCategory = getExistingCategory(id);
		categoryRepository.delete(existingCategory);

	}

	public Category updateCategory(@Valid Category category) {

		return categoryRepository.save(category);
	}

	public Category partialUpdateCategory(Category category) {

		if (null == category.getId()) {
			throw new IllegalArgumentException("id is mandatory field for update.");
		}

		Category existingCategory = getExistingCategory(category.getId());

		if (null != category.getName()) {
			existingCategory.setName(category.getName());
		}

		if (null != category.getDescription()) {
			existingCategory.setDescription(category.getDescription());
		}

		if (null != category.getSchemaLocation()) {
			existingCategory.setSchemaLocation(category.getSchemaLocation());
		}

		if (null != category.getVersion()) {
			existingCategory.setVersion(category.getVersion());
		}

		if (null != category.getValidFor()) {
			existingCategory.setValidFor(category.getValidFor());
		}

		if (null != category.getLifecycleStatus()) {
			existingCategory.setLifecycleStatus(category.getLifecycleStatus());
		}

		if (null != category.getParentId()) {
			existingCategory.setParentId(category.getParentId());
		}

		if (null != category.getSubCategory()) {
			existingCategory.setSubCategory(category.getSubCategory());
		}

		if (null != category.getProductOffering()) {
			existingCategory.setProductOffering(category.getProductOffering());
		}

		return saveCategory(existingCategory);

	}

	private Category saveCategory(@Valid Category category) {
		return categoryRepository.save(category);
	}

	private void setDefaultValues(Category category) {
		if (null == category.getType() || category.getType().trim().equals("")) {
			category.setType("Category");
		}

		if (null == category.getVersion()) {
			category.setVersion("1.0");
		}

		if (null == category.getIsRoot()) {
			category.setIsRoot(true);
		}
	}

	private Category getExistingCategory(@NotNull String id) {
		Optional<Category> existingCategoryOption = categoryRepository.findById(id);
		if (!existingCategoryOption.isPresent()) {
			throw new IllegalArgumentException("Category with id " + id + " doesnot exists");
		}

		Category existingCategory = existingCategoryOption.get();
		return existingCategory;
	}

}
