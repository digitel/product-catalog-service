package org.tmf.openapi.catalog.domain;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.tmf.openapi.catalog.domain.common.LifeCycleStatus;
import org.tmf.openapi.catalog.domain.common.RelatedPartyRef;
import org.tmf.openapi.catalog.domain.common.ResourceSpecificationRef;
import org.tmf.openapi.catalog.domain.common.ServiceSpecificationRef;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductSpecification {

	@NotEmpty
	private String id;

	@NotEmpty
	private String href;

	private String name;

	private String description;

	private String brand;

	private boolean isBundle;

	private Date lastUpdate;

	private LifeCycleStatus lifecycleStatus;

	// TODO Unique constraint based on db.
	private String productNumber;

	@NotNull
	@Valid
	private TimePeriod validFor;

	private String version;

	@JsonProperty("@type")
	private String type;

	@JsonProperty("@baseType")
	private String baseType;

	@JsonProperty("@schemaLocation")
	private String schemaLocation;

	private List<RelatedPartyRef> relatedParty;

	private List<Attachment> attachment;

	private List<ProductSpecCharacteristic> productSpecCharacteristic;

	private List<ServiceSpecificationRef> serviceSpecification;
	private List<ProductSpecificationRelationship> productSpecificationRelationship;
	private List<ResourceSpecificationRef> resourceSpecification;

	private List<BundledProductSpecificationRef> bundledProductSpecification;

}
