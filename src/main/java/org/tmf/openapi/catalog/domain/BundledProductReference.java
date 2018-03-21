package org.tmf.openapi.catalog.domain;

import org.tmf.openapi.catalog.domain.common.BaseRef;
import org.tmf.openapi.catalog.domain.common.LifeCycleStatus;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class BundledProductReference extends BaseRef {

	private LifeCycleStatus lifeCycleStatus;

}
