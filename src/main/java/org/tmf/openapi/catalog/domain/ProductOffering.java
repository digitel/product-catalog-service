package org.tmf.openapi.catalog.domain;

import java.net.URI;
import java.util.Date;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.tmf.openapi.catalog.domain.common.AgreementRef;
import org.tmf.openapi.catalog.domain.common.CategoryRef;
import org.tmf.openapi.catalog.domain.common.ChannelRef;
import org.tmf.openapi.catalog.domain.common.LifeCycleStatus;
import org.tmf.openapi.catalog.domain.common.MarketSegmentRef;
import org.tmf.openapi.catalog.domain.common.PlaceRef;
import org.tmf.openapi.catalog.domain.common.ProductSpecificationRef;
import org.tmf.openapi.catalog.domain.common.ResourceCandidateRef;
import org.tmf.openapi.catalog.domain.common.SLARef;
import org.tmf.openapi.catalog.domain.common.ServiceCandidateRef;
import org.tmf.openapi.catalog.validator.ValidProductOffering;

import com.fasterxml.jackson.annotation.JsonFilter;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.querydsl.core.annotations.QueryEntity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ValidProductOffering
@Document
@JsonFilter("productOfferingFilter")
@EqualsAndHashCode(of = "id")
@ToString(includeFieldNames = true)
@QueryEntity
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

	private LifeCycleStatus lifecycleStatus;

	@JsonProperty("@type")
	private String type;

	@JsonProperty("@baseType")
	private String baseType;

	@JsonProperty("@schemaLocation")
	private String schemaLocation;

	private Boolean isSellable;

	@Valid
	private TimePeriod validFor;

	@Valid
	private List<CategoryRef> category;

	private Boolean isBundle;

	private List<BundledProductReference> bundledProductOffering;

	private ProductSpecificationRef productSpecification;

	private List<ChannelRef> channel;

	private ServiceCandidateRef serviceCandidate;

	private ResourceCandidateRef resourceCandidate;

	private List<Attachment> attachment;

	private List<MarketSegmentRef> marketSegment;

	private List<PlaceRef> place;

	private SLARef serviceLevelAgreement;

	private List<AgreementRef> agreement;

	private List<ProductOfferingTerm> productOfferingTerm;

	private List<ProductOfferingPrice> productOfferingPrice;

	private String recurringChargePeriod;

	private List<ProdSpecCharValueUse> prodSpecCharValueUse;

}
