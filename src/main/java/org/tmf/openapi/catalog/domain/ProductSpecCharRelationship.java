package org.tmf.openapi.catalog.domain;

import javax.validation.Valid;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductSpecCharRelationship {

	private String type;

	@Valid
	private TimePeriod validFor;

	private int charSpecSeq;
	private String id;
	private String name;
	private String href;

	@JsonProperty("@type")
	private String at_type;

}
