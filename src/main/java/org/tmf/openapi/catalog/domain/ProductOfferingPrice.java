package org.tmf.openapi.catalog.domain;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.tmf.openapi.catalog.domain.common.ConstraintRef;
import org.tmf.openapi.catalog.domain.common.LifeCycleStatus;
import org.tmf.openapi.catalog.domain.common.PlaceRef;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductOfferingPrice {

	private String name;

	private String description;

	@NotEmpty
	private String id;

	@NotEmpty
	private String href;

	@JsonProperty("@type")
	private String type;

	@JsonProperty("@baseType")
	private String baseType;

	@JsonProperty("@schemaLocation")
	private String schemaLocation;

	private boolean isBundle;

	@NotNull
	@Valid
	private TimePeriod validFor;

	private ProductOfferingPriceType priceType;

	private String unitOfMeasure;

	private Price price;

	private List<PriceAlteration> priceAlteration;

	private String recurringChargePeriod;

	private String recurringChargePeriodType; // TODO Enum

	private int recurringChargePeriodLength;

	private String version;

	private Date lastUpdate;

	private LifeCycleStatus lifecycleStatus;

	private float percentage;

	private List<BundledPopRelationship> bundledPopRelationship;

	private List<PopRelationship> popRelationship;

	private List<ProdSpecCharValueUse> prodSpecCharValueUse;

	private List<ProductOfferingTerm> productOfferingTerm;

	private List<PlaceRef> place;

	private List<ConstraintRef> constraint;

	private List<PricingLogicAlgorithm> pricingLogicAlgorithm;
	
	private List<Tax> tax;

}
