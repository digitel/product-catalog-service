package org.tmf.openapi.catalog.domain;

import java.util.List;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductSpecCharacteristic {

	private String name;
	private String description;
	private String valueType; // TODO detail out type of values and can we create an Enum of possible values.
	private boolean configurable;
	
	@Valid
	private TimePeriod validFor;

	@JsonProperty("@type")
	private String type;

	@JsonProperty("@schemaLocation")
	private String schemaLocation;

	@JsonProperty("@valueSchemaLocation")
	private String valueSchemaLocation;

	private Integer minCardinality;
	private Integer maxCardinality;
	private boolean isUnique;
	private String regex;
	private boolean extensible;
	private List<ProductSpecCharRelationship> productSpecCharRelationship;
	private List<ProductSpecCharacteristicValue > productSpecCharacteristicValue;

}
