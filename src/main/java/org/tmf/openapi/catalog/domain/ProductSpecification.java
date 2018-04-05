package org.tmf.openapi.catalog.domain;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tmf.openapi.catalog.domain.common.RelatedPartyRef;
import org.tmf.openapi.catalog.domain.common.ResourceSpecificationRef;
import org.tmf.openapi.catalog.domain.common.ServiceSpecificationRef;
import org.tmf.openapi.catalog.validator.ValidProductSpecification;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@Document
@JsonFilter("productSpecificationFilter")
@EqualsAndHashCode(of = "id")
@ToString(includeFieldNames = true)
@QueryEntity
@ValidProductSpecification
public class ProductSpecification {

	private String id;

	@Transient
	private URI href;

	@NotEmpty
	private String name;

	private String description;

	private String brand;

	private Boolean isBundle;

	private Date lastUpdate;

	private String lifecycleStatus;

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
