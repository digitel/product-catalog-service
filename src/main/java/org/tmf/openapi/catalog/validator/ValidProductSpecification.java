package org.tmf.openapi.catalog.validator;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ProductSpecificationValidator.class })

public @interface ValidProductSpecification {

	String message() default "{org.tmf.openapi.catlog.validation.productOffering}";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
