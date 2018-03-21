package org.tmf.openapi.catalog.domain.common;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class PlaceRef extends BaseRef {

	private String geoLocationUrl;

	private String address;

	private String role;

}
