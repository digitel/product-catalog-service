package org.tmf.openapi.catalog.domain.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class ProductSpecificationRef extends BaseRef {

	private String version;

}
