package org.tmf.openapi.catalog.domain.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TargetProductSchemaRef {

	@JsonProperty("@referredType")
	private String referredType;

	@JsonProperty("@schemaLocation")
	private String schemaLocation;

}
