package org.tmf.openapi.catalog.domain;

import lombok.Data;

@Data
public class BundledProdOfferOption {

	private int numberRelOfferLowerLimit;
	private int numberRelOfferUpperLimit;
	private int numberRelOfferDefault;
}
