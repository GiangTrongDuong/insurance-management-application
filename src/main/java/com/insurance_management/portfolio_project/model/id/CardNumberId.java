package com.insurance_management.portfolio_project.model.id;

import com.insurance_management.portfolio_project.utils.generators.CardNumberGenerator;
import org.hibernate.annotations.IdGeneratorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;

@IdGeneratorType(CardNumberGenerator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({FIELD, METHOD})

public @interface CardNumberId {

}
