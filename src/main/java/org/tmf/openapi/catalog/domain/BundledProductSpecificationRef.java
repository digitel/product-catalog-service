package org.tmf.openapi.catalog.domain;

import javax.validation.constraints.NotEmpty;

import org.tmf.openapi.catalog.domain.common.LifeCycleStatus;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class BundledProductSpecificationRef {

	@NotEmpty
	private String id;

	@NotEmpty
	private String href;

	private LifeCycleStatus lifecycleStatus;

	private String name;

	@JsonProperty("@type")
	private String type;

}
