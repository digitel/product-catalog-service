package org.tmf.openapi.catalog.domain;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class PriceAlteration {

	private int applicationDuration;

	private String description;

	private String name;

	private String priceCondition;

	private String priceType; // TODO Enum.

	private int priority;

	private String recurringChargePeriod; // TODO Enum.

	private String unitOfMeasure; // TODO Enum.

	@NotNull
	@Valid
	private TimePeriod validFor;

	private Price price;
}
