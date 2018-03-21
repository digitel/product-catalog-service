package org.tmf.openapi.catalog.domain;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductOfferingTerm {

	private String name;

	private String description;

	private String duration;

	private TimePeriod validFor;
	
	private ProductOfferingPriceType priceType; //TODO Validate if this field is required

	@JsonProperty("@type")
	private String type;

	@JsonProperty("@schemaLocation")
	private String schemaLocation;

}
