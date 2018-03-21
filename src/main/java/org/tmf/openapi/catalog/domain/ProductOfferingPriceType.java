package org.tmf.openapi.catalog.domain;

import com.fasterxml.jackson.annotation.JsonValue;


public enum ProductOfferingPriceType {
	RECURRING("recurring"), ONE_TIME("one time"), USAGE("usage");

	private String value;

	private ProductOfferingPriceType(String value) {
		this.value = value;
	}

	@Override
	public String toString() {
		return value;
	}

	@JsonValue(true)
	public String getValue() {
		return this.value;
	}

	public static ProductOfferingPriceType find(String value) {
		for (ProductOfferingPriceType productOfferingPriceType : values()) {
			if (productOfferingPriceType.value.equals(value)) {
				return productOfferingPriceType;
			}
		}

		return null;
	}

}
