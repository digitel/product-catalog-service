package org.tmf.openapi.catalog.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.tmf.openapi.catalog.domain.catalog.Category;

public class CategoryValidator implements ConstraintValidator<ValidCategory, Category> {

	@Override
	public boolean isValid(Category category, ConstraintValidatorContext context) {

		if (null == category || null == category.getIsRoot()) {
			return true;
		}
		
		if (false == category.getIsRoot()) {

			if (null == category.getParentId() || "".equals(category.getParentId().trim())) {
				return false;
			}
		}
		else {
			
			if (null != category.getParentId() || !"".equals(category.getParentId().trim())) {
				return false;
			}
		}
		return true;
	}
}