package org.tmf.openapi.catalog.domain;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.tmf.openapi.catalog.domain.common.CategoryRef;
import org.tmf.openapi.catalog.domain.common.ChannelRef;
import org.tmf.openapi.catalog.domain.common.LifeCycleStatus;
import org.tmf.openapi.catalog.domain.common.MarketSegmentRef;
import org.tmf.openapi.catalog.domain.common.PlaceRef;
import org.tmf.openapi.catalog.domain.common.ProductSpecificationRef;
import org.tmf.openapi.catalog.domain.common.ResourceCandidateRef;
import org.tmf.openapi.catalog.domain.common.SLARef;
import org.tmf.openapi.catalog.domain.common.ServiceCandidateRef;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProductOffering {

	@Id
	private String id;

	@Transient
	private URI href;

	private String version;

	private Date lastUpdate;

	@NotEmpty
	private String name;

	private String description;

	private boolean isBundle;

	private LifeCycleStatus lifecycleStatus;

	@JsonProperty("@type")
	private String type;

	@JsonProperty("@baseType")
	private String baseType;

	@JsonProperty("@schemaLocation")
	private String schemaLocation;

	private Boolean isSellable;

	@NotNull
	@Valid
	private TimePeriod validFor;

	@Valid
	private List<CategoryRef> category;

	private List<ChannelRef> channel;

	private ServiceCandidateRef serviceCandidate;

	private ResourceCandidateRef resourceCandidate;

	private List<Attachment> attachment;

	private List<MarketSegmentRef> marketSegment;

	private List<PlaceRef> place;

	private List<BundledProductReference> bundledProductOffering;

	private SLARef serviceLevelAgreement;

	private ProductSpecificationRef productSpecification;

	private List<ProductOfferingTerm> productOfferingTerm;

	private List<ProductOfferingPrice> productOfferingPrice;

	private String recurringChargePeriod;

	private List<ProdSpecCharValueUse> prodSpecCharValueUse;

}
