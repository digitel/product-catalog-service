package org.tmf.openapi.catalog.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.tmf.openapi.catalog.common.ObjectMapper.mapObjectWithExcludeFilter;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.querydsl.binding.QuerydslPredicate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.tmf.openapi.catalog.domain.ProductSpecification;
import org.tmf.openapi.catalog.service.ProductSpecificationService;

import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/catalogManagement/productSpecification")
public class ProductSpecificationController {

	@Autowired
	private ProductSpecificationService productSpecificationService;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> getProductSpecification(
			@RequestParam MultiValueMap<String, String> requestParams, Pageable pageable,
			@QuerydslPredicate(root = ProductSpecification.class) Predicate predicate) {
		return ResponseEntity.ok(mapObjectWithExcludeFilter(
				populateHref(productSpecificationService.findAllProductSpecifications(predicate)), requestParams,
				"productSpecificationFilter"));

	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> getProductSpecification(@PathVariable String id,
			@RequestParam MultiValueMap<String, String> requestParams) {
		return ResponseEntity
				.ok(mapObjectWithExcludeFilter(populateHref(productSpecificationService.findProductSpecification(id)),
						requestParams, "productSpecificationFilter"));

	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> createProductSpecification(
			@RequestBody @Valid ProductSpecification productSpecification) throws URISyntaxException {

		productSpecification = productSpecificationService.createProductSpecification(productSpecification);

		linkTo(ProductSpecificationController.class).toUri().relativize(populateHref(productSpecification).getHref());

		return ResponseEntity
				.created(new URI("/catalogManagement/productSpecification/" + productSpecification.getId()))
				.body(mapObjectWithExcludeFilter(productSpecification, null, "productSpecificationFilter"));
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> updateProductSpecification(@PathVariable String id,
			@RequestBody ProductSpecification productSpecification) {

		validateProductSpecification(id, productSpecification);
		return ResponseEntity.ok(mapObjectWithExcludeFilter(
				populateHref(productSpecificationService.updateProductSpecification(productSpecification)), null,
				"productSpecificationFilter"));

	}

	@PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> patchProductSpecification(@PathVariable String id,
			@RequestBody ProductSpecification productSpecification) {

		validateProductSpecification(id, productSpecification);
		return ResponseEntity.ok(mapObjectWithExcludeFilter(
				populateHref(productSpecificationService.partialUpdateProductSpecification(productSpecification)), null,
				"productSpecificationFilter"));

	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> deleteProductSpecification(@PathVariable String id) {
		productSpecificationService.deleteProductSpecification(id);
		return ResponseEntity.noContent().build();

	}

	private ProductSpecification populateHref(ProductSpecification productSpecification) {
		productSpecification
				.setHref(linkTo(ProductSpecificationController.class).slash(productSpecification.getId()).toUri());
		return productSpecification;
	}

	private List<ProductSpecification> populateHref(List<ProductSpecification> productSpecifications) {
		for (ProductSpecification productSpecification : productSpecifications) {
			populateHref(productSpecification);
		}
		return productSpecifications;
	}

	private void validateProductSpecification(String id, ProductSpecification productSpecification) {
		if ((null == productSpecification.getId())
				|| (null != productSpecification.getId() && !productSpecification.getId().equals(id))) {
			throw new IllegalArgumentException("id cannot be updated.");
		}
	}

}
