package org.tmf.openapi.catalog.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.tmf.openapi.catalog.domain.ProductSpecification;

public class ProductSpecificationValidator
		implements ConstraintValidator<ValidProductSpecification, ProductSpecification> {

	@Override
	public boolean isValid(ProductSpecification productSpecification, ConstraintValidatorContext context) {

		if (null == productSpecification)
			return true;

		if (null == productSpecification.getIsBundle())
			return false;

		if (true == productSpecification.getIsBundle()) {

			if (null == productSpecification.getBundledProductSpecification()
					|| 0 == productSpecification.getBundledProductSpecification().size()) {

				return false;
			}
		}

		return true;
	}
}