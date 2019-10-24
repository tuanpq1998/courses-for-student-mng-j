/**
 * @author admin
 * @date 06-10-2019
 */

package com.tuanpq.coursemng.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Constraint(validatedBy = UsernameValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface UniqueUsername {

	public String message() default "Invalid course name!";
	public Class<?>[] groups() default {};
	public Class<? extends Payload>[] payload() default {};
}
