package org.tmf.openapi.catalog.domain.common;

import javax.validation.Valid;

import org.tmf.openapi.catalog.domain.TimePeriod;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class CategoryRef extends BaseRef {

	private String version;

	@Valid
	private TimePeriod validFor;

}
