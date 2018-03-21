package org.tmf.openapi.catalog.domain;

import java.net.URI;

import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.tmf.openapi.catalog.domain.common.LifeCycleStatus;

import lombok.Data;

@Data
public class BundledProductOffering {

	@Id
	private String id;

	@Transient
	private URI href;

	@NotEmpty
	private String name;

	private LifeCycleStatus lifecycleStatus;

	private BundledProdOfferOption bundledProductOfferingOption;

}
