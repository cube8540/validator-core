package cube8540.validator.core;

import java.util.Objects;

public class Validator<T> {

    private final T target;
    private final ValidationResult result;

    private Validator(T target) {
        this.target = Objects.requireNonNull(target);
        this.result = ValidationResult.init();
    }

    public Validator<T> registerRule(ValidationRule<T> registerRule) {
        if (!registerRule.isValid(target)) {
            this.result.registerError(registerRule.error());
        }
        return this;
    }

    public T getTarget() {
        return target;
    }

    public ValidationResult getResult() {
        return result;
    }

    public static <T> Validator<T> of(T object) {
        return new Validator<>(object);
    }
}
