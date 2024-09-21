package dw.trabalho.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdadeLimiteValidator.class)
public @interface IdadeLimite {
    int idadeMinima() default 18;
    String message() default "a idade mínima é 18 anos";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
