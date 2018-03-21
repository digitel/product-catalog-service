package org.tmf.openapi.catalog.web;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.tmf.openapi.catalog.common.ObjectMapper.mapObjectWithExcludeFilter;

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
import org.tmf.openapi.catalog.domain.catalog.Catalog;
import org.tmf.openapi.catalog.service.CatalogService;

import com.querydsl.core.types.Predicate;

@RestController
@RequestMapping("/catalogManagement/catalog")
public class CatalogController {

	@Autowired
	private CatalogService catalogService;

	@GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> getCatalog(@RequestParam MultiValueMap<String, String> requestParams,
			Pageable pageable, @QuerydslPredicate(root = Catalog.class) Predicate predicate) {
		return ResponseEntity.ok(mapObjectWithExcludeFilter(populateHref(catalogService.findAllCatalogs(predicate)),
				requestParams, "catalogFilter"));

	}

	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> getCalalog(@PathVariable String id,
			@RequestParam MultiValueMap<String, String> requestParams) {
		return ResponseEntity.ok(mapObjectWithExcludeFilter(populateHref(catalogService.findCatalog(id)), requestParams,
				"catalogFilter"));

	}

	@PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> createCatalog(@RequestBody @Valid Catalog catalog)
			throws URISyntaxException {

		catalog = catalogService.createCatalog(catalog);
		return ResponseEntity.created(populateHref(catalog).getHref())
				.body(mapObjectWithExcludeFilter(catalog, null, "catalogFilter"));
	}

	@PutMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> updateCatalog(@PathVariable String id, @RequestBody Catalog catalog) {

		validateCatalog(id, catalog);
		return ResponseEntity.ok(
				mapObjectWithExcludeFilter(populateHref(catalogService.updateCatalog(catalog)), null, "catalogFilter"));

	}

	@PatchMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> patchCatalog(@PathVariable String id, @RequestBody Catalog catalog) {

		validateCatalog(id, catalog);
		return ResponseEntity.ok(mapObjectWithExcludeFilter(populateHref(catalogService.partialUpdateCatalog(catalog)),
				null, "catalogFilter"));

	}

	@DeleteMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<MappingJacksonValue> deleteCatalog(@PathVariable String id) {
		catalogService.deleteCatalog(id);
		return ResponseEntity.noContent().build();

	}

	private Catalog populateHref(Catalog catalog) {
		catalog.setHref(linkTo(CatalogController.class).slash(catalog.getId()).toUri());
		return catalog;
	}

	private List<Catalog> populateHref(List<Catalog> catalogs) {
		for (Catalog catalog : catalogs) {
			populateHref(catalog);
		}
		return catalogs;
	}

	private void validateCatalog(String id, Catalog catalog) {
		if ((null == catalog.getId()) || (null != catalog.getId() && !catalog.getId().equals(id))) {
			throw new IllegalArgumentException("id cannot be updated.");
		}
	}

}
