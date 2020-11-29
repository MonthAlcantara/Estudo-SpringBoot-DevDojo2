package io.github.monthalcantara.springboot2essentials.compartilhado.validators;

import javax.persistence.EntityManager;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.List;
import java.util.Optional;

public class ExisteIdValidator implements ConstraintValidator<ExisteId, Object> {

    private final EntityManager manager;
    private Class<?> classe;
    private String atributo;

    public ExisteIdValidator(EntityManager manager) {
        this.manager = manager;
    }

    @Override
    public void initialize(ExisteId constraintAnnotation) {
    this.atributo = constraintAnnotation.atributo();
    this.classe = constraintAnnotation.classe();
    }

    @Override
    public boolean isValid(Object id, ConstraintValidatorContext context) {
        List resultList = manager.createQuery("Select x from " + classe.getSimpleName() + " x where " + atributo + " =:id").setParameter("id", id).getResultList();
        return !resultList.isEmpty();


    }
}
