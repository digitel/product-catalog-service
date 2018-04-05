package org.tmf.openapi.catalog.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.tmf.openapi.catalog.common.ObjectMapper.mapObjectWithExcludeFilter;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
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
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.tmf.openapi.catalog.domain.ProductOffering;
import org.tmf.openapi.catalog.service.ProductOfferingService;

import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/catalogManagement/productOffering")
public class ProductOfferingController {

	@Autowired
	private ProductOfferingService productOfferingService;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> getProductOffering(
			@RequestParam MultiValueMap<String, String> requestParams, Pageable pageable,
			@QuerydslPredicate(root = ProductOffering.class) Predicate predicate) {
		return ResponseEntity
				.ok(mapObjectWithExcludeFilter(populateHref(productOfferingService.findAllProductOfferings(predicate)),
						requestParams, "productOfferingFilter"));

	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> getProductOffering(@PathVariable String id,
			@RequestParam MultiValueMap<String, String> requestParams) {
		return ResponseEntity.ok(mapObjectWithExcludeFilter(
				populateHref(productOfferingService.findProductOffering(id)), requestParams, "productOfferingFilter"));

	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> createProductOffering(
			@RequestBody @Valid ProductOffering productOffering) throws URISyntaxException {

		productOffering = productOfferingService.createProductOffering(productOffering);

		linkTo(ProductOfferingController.class).toUri().relativize(populateHref(productOffering).getHref());
		getCurrentRequest().getServletPath();

		// return ResponseEntity.created(populateHref(productOffering).getHref())
		// .body(mapObjectWithExcludeFilter(productOffering, null,
		// "productOfferingFilter"));

		return ResponseEntity.created(new URI("/catalogManagement/productOffering/" + productOffering.getId()))
				.body(mapObjectWithExcludeFilter(productOffering, null, "productOfferingFilter"));
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> updateProductOffering(@PathVariable String id,
			@RequestBody ProductOffering productOffering) {

		validateProductOffering(id, productOffering);
		return ResponseEntity.ok(
				mapObjectWithExcludeFilter(populateHref(productOfferingService.updateProductOffering(productOffering)),
						null, "productOfferingFilter"));

	}

	@PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> patchProductOffering(@PathVariable String id,
			@RequestBody ProductOffering productOffering) {

		validateProductOffering(id, productOffering);
		return ResponseEntity.ok(mapObjectWithExcludeFilter(
				populateHref(productOfferingService.partialUpdateProductOffering(productOffering)), null,
				"catalogFilter"));

	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> deleteCatalog(@PathVariable String id) {
		productOfferingService.deleteProductOffering(id);
		return ResponseEntity.noContent().build();

	}

	@SuppressWarnings("null")
	private static HttpServletRequest getCurrentRequest() {

		RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
		HttpServletRequest servletRequest = ((ServletRequestAttributes) requestAttributes).getRequest();
		return servletRequest;
	}

	private ProductOffering populateHref(ProductOffering productOffering) {
		productOffering.setHref(linkTo(ProductOfferingController.class).slash(productOffering.getId()).toUri());
		return productOffering;
	}

	private List<ProductOffering> populateHref(List<ProductOffering> productOfferings) {
		for (ProductOffering productOffering : productOfferings) {
			populateHref(productOffering);
		}
		return productOfferings;
	}

	private void validateProductOffering(String id, ProductOffering productOffering) {
		if ((null == productOffering.getId())
				|| (null != productOffering.getId() && !productOffering.getId().equals(id))) {
			throw new IllegalArgumentException("id cannot be updated.");
		}
	}

}
