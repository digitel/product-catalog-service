package org.tmf.openapi.catalog.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.tmf.openapi.catalog.domain.ProductOffering;

public class ProductOfferingValidator implements ConstraintValidator<ValidProductOffering, ProductOffering> {

	@Override
	public boolean isValid(ProductOffering productOffering, ConstraintValidatorContext context) {

		if (null == productOffering)
			return true;

		if (null != productOffering.getIsBundle() && productOffering.getIsBundle()
				&& (null == productOffering.getBundledProductOffering()
						|| 0 == productOffering.getBundledProductOffering().size())) {
			return false;

		} else if (null == productOffering.getProductSpecification()) {

			return false;
		}
		return true;
	}
}