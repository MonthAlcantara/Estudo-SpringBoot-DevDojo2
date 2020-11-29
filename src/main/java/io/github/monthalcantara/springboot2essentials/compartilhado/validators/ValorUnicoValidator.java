package io.github.monthalcantara.springboot2essentials.compartilhado.validators;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;

public class ValorUnicoValidator implements ConstraintValidator<ValorUnico, Object> {

    private final EntityManager manager;
    private Class<?> classe;
    private String atributo;

    public ValorUnicoValidator(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void initialize(ValorUnico constraintAnnotation) {
        this.atributo = constraintAnnotation.atributo();
        this.classe = constraintAnnotation.classe();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        List resultList = manager.createQuery("Select x from " + classe.getSimpleName() + " x where " + atributo + " =:value").setParameter("value", value).getResultList();
        return resultList.isEmpty();
    }
}
