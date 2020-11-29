package io.github.monthalcantara.springboot2essentials.compartilhado.validators;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {ValorUnicoValidator.class})
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValorUnico {


    String message() default "{io.github.monthalcantara.beanvalidation.valorunico}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    Class<?> classe();

    String atributo();
}
