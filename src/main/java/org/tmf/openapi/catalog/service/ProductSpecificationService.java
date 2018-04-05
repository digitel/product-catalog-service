package org.tmf.openapi.catalog.service;

import static org.tmf.openapi.catalog.common.ListUtils.toList;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmf.openapi.catalog.domain.ProductSpecification;
import org.tmf.openapi.catalog.domain.common.LifeCycleStatus;
import org.tmf.openapi.catalog.repository.ProductSpecificationRepository;

import com.querydsl.core.types.Predicate;

@Service
public class ProductSpecificationService {

	@Autowired
	private ProductSpecificationRepository productSpecificationRepository;

	public ProductSpecification createProductSpecification(@Valid ProductSpecification productSpecification) {

		if (productSpecification.getId() != null) {
			throw new IllegalArgumentException("id must be empty while creating ProductSpecification");
		}

		setDefaultValues(productSpecification);

		return productSpecificationRepository.save(productSpecification);
	}

	public ProductSpecification findProductSpecification(@NotNull String id) {
		return productSpecificationRepository.findById(id).get();
	}

	public List<ProductSpecification> findAllProductSpecifications(Predicate predicate) {

		if (null == predicate) {
			return productSpecificationRepository.findAll();
		}
		return toList(productSpecificationRepository.findAll(predicate));
	}

	public void deleteProductSpecification(@NotNull String id) {

		ProductSpecification existingProductSpecification = getExistingProductSpecification(id);
		productSpecificationRepository.delete(existingProductSpecification);

	}

	public ProductSpecification updateProductSpecification(@Valid ProductSpecification productSpecification) {

		return saveProductSpecification(productSpecification);
	}

	public ProductSpecification partialUpdateProductSpecification(ProductSpecification productSpecification) {

		if (null == productSpecification.getId()) {
			throw new IllegalArgumentException("id is mandatory field for update.");
		}

		ProductSpecification existingProductSpecification = getExistingProductSpecification(
				productSpecification.getId());

		if (null != productSpecification.getName()) {
			existingProductSpecification.setName(productSpecification.getName());
		}

		if (null != productSpecification.getDescription()) {
			existingProductSpecification.setDescription(productSpecification.getDescription());
		}

		if (null != productSpecification.getBrand()) {
			existingProductSpecification.setBrand(productSpecification.getBrand());
		}
		if (null != productSpecification.getIsBundle()) {
			existingProductSpecification.setIsBundle(productSpecification.getIsBundle());
		}

		if (null != productSpecification.getLifecycleStatus()) {
			existingProductSpecification.setLifecycleStatus(productSpecification.getLifecycleStatus());
		}

		if (null != productSpecification.getProductNumber()) {
			existingProductSpecification.setProductNumber(productSpecification.getProductNumber());
		}

		if (null != productSpecification.getValidFor()) {
			existingProductSpecification.setValidFor(productSpecification.getValidFor());
		}

		if (null != productSpecification.getVersion()) {
			existingProductSpecification.setVersion(productSpecification.getVersion());
		}

		if (null != productSpecification.getSchemaLocation()) {
			existingProductSpecification.setSchemaLocation(productSpecification.getSchemaLocation());
		}

		if (null != productSpecification.getRelatedParty()) {
			existingProductSpecification.setRelatedParty(productSpecification.getRelatedParty());
		}

		if (null != productSpecification.getProductSpecCharacteristic()) {
			existingProductSpecification
					.setProductSpecCharacteristic(productSpecification.getProductSpecCharacteristic());
		}

		if (null != productSpecification.getProductSpecificationRelationship()) {
			existingProductSpecification
					.setProductSpecificationRelationship(productSpecification.getProductSpecificationRelationship());
		}

		if (null != productSpecification.getResourceSpecification()) {
			existingProductSpecification.setResourceSpecification(productSpecification.getResourceSpecification());
		}

		if (null != productSpecification.getAttachment()) {
			existingProductSpecification.setAttachment(productSpecification.getAttachment());
		}

		if (null != productSpecification.getBundledProductSpecification()) {
			existingProductSpecification
					.setBundledProductSpecification(productSpecification.getBundledProductSpecification());
		}

		return saveProductSpecification(existingProductSpecification);

	}

	private ProductSpecification saveProductSpecification(@Valid ProductSpecification productSpecification) {
		return productSpecificationRepository.save(productSpecification);
	}

	private void setDefaultValues(ProductSpecification productSpecification) {

		if (null == productSpecification.getIsBundle()) {
			productSpecification.setIsBundle(false);
		}

		if (null == productSpecification.getType()) {
			productSpecification.setType("ProductSpecification");
		}
		if (null == productSpecification.getLifecycleStatus()) {
			productSpecification.setLifecycleStatus("In Study");
		}
	}

	private ProductSpecification getExistingProductSpecification(@NotNull String id) {
		Optional<ProductSpecification> existingProductSpecificationOption = productSpecificationRepository.findById(id);
		if (!existingProductSpecificationOption.isPresent()) {
			throw new IllegalArgumentException("ProductSpecification with id " + id + " doesnot exists");
		}

		ProductSpecification existingProductSpecification = existingProductSpecificationOption.get();
		return existingProductSpecification;
	}

}
