package org.tmf.openapi.catalog.service;

import static org.tmf.openapi.catalog.common.ListUtils.toList;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmf.openapi.catalog.domain.ProductOffering;
import org.tmf.openapi.catalog.repository.ProductOfferingRepository;

import com.querydsl.core.types.Predicate;

@Service
public class ProductOfferingService {

	@Autowired
	private ProductOfferingRepository productOfferingRepository;

	public ProductOffering createProductOffering(@Valid ProductOffering productOffering) {

		if (productOffering.getId() != null) {
			throw new IllegalArgumentException("id must be empty while creating ProductOffering");
		}

		setDefaultValues(productOffering);

		return productOfferingRepository.save(productOffering);
	}

	public ProductOffering findProductOffering(@NotNull String id) {
		return productOfferingRepository.findById(id).get();
	}

	public List<ProductOffering> findAllProductOfferings(Predicate predicate) {

		if (null == predicate) {
			return productOfferingRepository.findAll();
		}
		return toList(productOfferingRepository.findAll(predicate));
	}

	public void deleteProductOffering(@NotNull String id) {

		ProductOffering existingProductOffering = getExistingProductOffering(id);
		productOfferingRepository.delete(existingProductOffering);

	}

	public ProductOffering updateProductOffering(@Valid ProductOffering productOffering) {

		return saveProductOffering(productOffering);
	}

	public ProductOffering partialUpdateProductOffering(ProductOffering productOffering) {

		if (null == productOffering.getId()) {
			throw new IllegalArgumentException("id is mandatory field for update.");
		}

		ProductOffering existingProductOffering = getExistingProductOffering(productOffering.getId());

		if (null != productOffering.getName()) {
			existingProductOffering.setName(productOffering.getName());
		}

		if (null != productOffering.getDescription()) {
			existingProductOffering.setDescription(productOffering.getDescription());
		}

		if (null != productOffering.getIsBundle()) {
			existingProductOffering.setIsBundle(productOffering.getIsBundle());
		}

		if (null != productOffering.getIsSellable()) {
			existingProductOffering.setIsSellable(productOffering.getIsSellable());
		}

		if (null != productOffering.getLifecycleStatus()) {
			existingProductOffering.setLifecycleStatus(productOffering.getLifecycleStatus());
		}

		if (null != productOffering.getValidFor()) {
			existingProductOffering.setValidFor(productOffering.getValidFor());
		}

		if (null != productOffering.getVersion()) {
			existingProductOffering.setVersion(productOffering.getVersion());
		}

		if (null != productOffering.getSchemaLocation()) {
			existingProductOffering.setSchemaLocation(productOffering.getSchemaLocation());
		}

		if (null != productOffering.getPlace()) {
			existingProductOffering.setPlace(productOffering.getPlace());
		}
		if (null != productOffering.getServiceLevelAgreement()) {
			existingProductOffering.setServiceLevelAgreement(productOffering.getServiceLevelAgreement());
		}
		if (null != productOffering.getProductSpecification()) {
			existingProductOffering.setProductSpecification(productOffering.getProductSpecification());
		}

		if (null != productOffering.getChannel()) {
			existingProductOffering.setChannel(productOffering.getChannel());
		}

		if (null != productOffering.getChannel()) {
			existingProductOffering.setChannel(productOffering.getChannel());
		}
		if (null != productOffering.getServiceCandidate()) {
			existingProductOffering.setServiceCandidate(productOffering.getServiceCandidate());
		}
		if (null != productOffering.getAttachment()) {
			existingProductOffering.setAttachment(productOffering.getAttachment());
		}
		if (null != productOffering.getCategory()) {
			existingProductOffering.setCategory(productOffering.getCategory());
		}

		if (null != productOffering.getResourceCandidate()) {
			existingProductOffering.setResourceCandidate(productOffering.getResourceCandidate());
		}

		if (null != productOffering.getProductOfferingTerm()) {
			existingProductOffering.setProductOfferingTerm(productOffering.getProductOfferingTerm());
		}

		if (null != productOffering.getMarketSegment()) {
			existingProductOffering.setMarketSegment(productOffering.getMarketSegment());
		}

		if (null != productOffering.getProductOfferingPrice()) {
			existingProductOffering.setProductOfferingPrice(productOffering.getProductOfferingPrice());
		}

		if (null != productOffering.getAgreement()) {
			existingProductOffering.setAgreement(productOffering.getAgreement());
		}

		if (null != productOffering.getBundledProductOffering()) {
			existingProductOffering.setBundledProductOffering(productOffering.getBundledProductOffering());
		}

		if (null != productOffering.getProdSpecCharValueUse()) {
			existingProductOffering.setProdSpecCharValueUse(productOffering.getProdSpecCharValueUse());
		}

		return saveProductOffering(existingProductOffering);

	}

	private ProductOffering saveProductOffering(@Valid ProductOffering productOffering) {
		return productOfferingRepository.save(productOffering);
	}

	private void setDefaultValues(ProductOffering productOffering) {

//		if (null == productOffering.getIsSellable()) {
//
//			productOffering.setIsSellable(true);
//		}

//		if (null == productOffering.getType() || productOffering.getType().trim().equals("")) {
//
//			productOffering.setType("ProductOffering");
//		}
		
		if (null == productOffering.getLifecycleStatus() || productOffering.getLifecycleStatus().trim().equals("")) {

			productOffering.setLifecycleStatus("Active");
		}

	}

	private ProductOffering getExistingProductOffering(@NotNull String id) {
		Optional<ProductOffering> existingProductOfferingOption = productOfferingRepository.findById(id);
		if (!existingProductOfferingOption.isPresent()) {
			throw new IllegalArgumentException("ProductOffering with id " + id + " doesnot exists");
		}

		ProductOffering existingProductOffering = existingProductOfferingOption.get();
		return existingProductOffering;
	}

}
