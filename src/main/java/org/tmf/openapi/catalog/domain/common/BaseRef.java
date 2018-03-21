package org.tmf.openapi.catalog.domain.common;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@EqualsAndHashCode(of = "id")
@ToString(includeFieldNames = true)
public abstract class BaseRef {

	@NotEmpty
	private String id;

	private String href;

	private String name;

	@JsonProperty("@referredType")
	private String referredType;

	private String description;

}