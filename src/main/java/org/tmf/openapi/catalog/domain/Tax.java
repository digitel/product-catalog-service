package org.tmf.openapi.catalog.domain;

import lombok.Data;

@Data
public class Tax {

	private Money taxAmount;

	private Money taxCategory;

	private float taxRate;
}
