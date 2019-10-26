package cube8540.validator.core.impl;

import cube8540.validator.core.ValidationError;
import cube8540.validator.core.ValidationRule;
import cube8540.validator.core.ValidationSpecification;

public class DefaultValidationRule<T> implements ValidationRule<T> {

    private final ValidationSpecification<T> specification;
    private final String property;
    private final String message;

    private DefaultValidationRule(ValidationSpecification<T> specification, String property, String message) {
        this.specification = specification;
        this.property = property;
        this.message = message;
    }

    @Override
    public ValidationError error() {
        return new ValidationError(property, message);
    }

    @Override
    public boolean isValid(T target) {
        return specification.isValid(target);
    }

    public static <T> DefaultValidationRuleBuilder<T> withSpecification(ValidationSpecification<T> specification) {
        return new DefaultValidationRuleBuilder<>(specification);
    }

    public static class DefaultValidationRuleBuilder<T> {
        private final ValidationSpecification<T> specification;
        private DefaultValidationRuleBuilder(ValidationSpecification<T> specification) {
            this.specification = specification;
        }

        public DefaultValidationRule<T> create(String property, String message) {
            return new DefaultValidationRule<>(specification, property, message);
        }
    }
}
