package org.tmf.openapi.catalog.domain;

import javax.validation.Valid;

import org.tmf.openapi.catalog.domain.common.BaseRef;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductSpecificationRelationship extends BaseRef{
	
	private String type;

	@Valid
	private TimePeriod validFor;

}
