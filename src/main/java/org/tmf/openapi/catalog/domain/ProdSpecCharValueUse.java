package org.tmf.openapi.catalog.domain;

import java.util.List;

import org.tmf.openapi.catalog.domain.common.ProductSpecificationRef;

import lombok.Data;

@Data
public class ProdSpecCharValueUse {

	private String name;

	private String description;

	private String valueType;

	int minCardinality;

	int maxCardinality;

	private TimePeriod validFor;

	private List<ProductSpecCharacteristicValue> productSpecCharacteristicValue;

	private ProductSpecificationRef productSpecification;
}
