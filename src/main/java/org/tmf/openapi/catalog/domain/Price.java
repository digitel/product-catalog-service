package org.tmf.openapi.catalog.domain;

import lombok.Data;

@Data
public class Price {

	private Money dutyFreeAmount;

	private float percentage;

	private Money taxIncludedAmount;

	private float taxRate;
}
