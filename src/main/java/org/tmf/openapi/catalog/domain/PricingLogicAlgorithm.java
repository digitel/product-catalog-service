package org.tmf.openapi.catalog.domain;

import javax.validation.Valid;

import org.tmf.openapi.catalog.domain.common.BaseRef;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PricingLogicAlgorithm extends BaseRef {

	@Valid
	private TimePeriod validFor;

	private String plaSpecId;

	@JsonProperty("@type")
	private String type;

}
