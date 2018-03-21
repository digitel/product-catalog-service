package org.tmf.openapi.catalog.service;

import static org.tmf.openapi.catalog.common.ListUtils.toList;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.tmf.openapi.catalog.domain.catalog.Catalog;
import org.tmf.openapi.catalog.repository.CatalogRepository;

import com.querydsl.core.types.Predicate;

@Service
public class CatalogService {

	@Autowired
	private CatalogRepository catalogRepository;

	public Catalog createCatalog(@Valid Catalog catalog) {

		if (catalog.getId() != null) {
			throw new IllegalArgumentException("id must be empty while creating Catalog");
		}

		setDefaultValues(catalog);
		return catalogRepository.save(catalog);
	}

	public Catalog findCatalog(@NotNull String id) {
		return catalogRepository.findById(id).get();
	}

	public List<Catalog> findAllCatalogs(Predicate predicate) {

		if (null == predicate) {
			return catalogRepository.findAll();
		}
		return toList(catalogRepository.findAll(predicate));
	}

	public void deleteCatalog(@NotNull String id) {
		Catalog existingCatalog = getExistingCatalog(id);
		catalogRepository.delete(existingCatalog);

	}

	public Catalog updateCatalog(@Valid Catalog catalog) {

		return catalogRepository.save(catalog);
	}

	public Catalog partialUpdateCatalog(Catalog catalog) {

		if (null == catalog.getId()) {
			throw new IllegalArgumentException("id is mandatory field for update.");
		}

		Catalog existingCatalog = getExistingCatalog(catalog.getId());

		if (null != catalog.getName()) {
			existingCatalog.setName(catalog.getName());
		}

		if (null != catalog.getDescription()) {
			existingCatalog.setDescription(catalog.getDescription());
		}

		if (null != catalog.getSchemaLocation()) {
			existingCatalog.setSchemaLocation(catalog.getSchemaLocation());
		}

		if (null != catalog.getVersion()) {
			existingCatalog.setVersion(catalog.getVersion());
		}

		if (null != catalog.getValidFor()) {
			existingCatalog.setValidFor(catalog.getValidFor());
		}

		if (null != catalog.getLifecycleStatus()) {
			existingCatalog.setLifecycleStatus(catalog.getLifecycleStatus());
		}

		return catalogRepository.save(existingCatalog);

	}

	private Catalog getExistingCatalog(@NotNull String id) {
		Optional<Catalog> existingCatalogOption = catalogRepository.findById(id);
		if (!existingCatalogOption.isPresent()) {
			throw new IllegalArgumentException("Catalog with id " + id + " doesnot exists");
		}

		Catalog existingCatalog = existingCatalogOption.get();
		return existingCatalog;
	}

	private void setDefaultValues(Catalog catalog) {
		if (null == catalog.getType() || catalog.getType().trim().equals("")) {
			catalog.setType("ProductCatalog");
		}
	}

}
