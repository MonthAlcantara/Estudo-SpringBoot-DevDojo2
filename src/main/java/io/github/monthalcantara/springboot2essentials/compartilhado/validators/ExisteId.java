package io.github.monthalcantara.springboot2essentials.compartilhado.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ExisteIdValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ExisteId {

    String message() default "{io.github.monthalcantara.beanvalidation.existeId}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> classe();

    String atributo();
}
