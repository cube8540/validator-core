package cube8540.validator.core;

import cube8540.validator.core.exception.ValidateException;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

@ToString
@EqualsAndHashCode
public class ValidationResult {

    private final List<ValidationError> errors;

    private ValidationResult() {
        this.errors = new ArrayList<>();
    }

    public List<ValidationError> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public ValidationResult registerError(ValidationError error) {
        this.errors.add(error);
        return this;
    }

    public boolean hasError() {
        return !errors.isEmpty();
    }

    public void hasErrorThrows(Function<List<ValidationError>, ValidateException> exceptionFunction) {
        if (hasError()) {
            throw exceptionFunction.apply(errors);
        }
    }

    static ValidationResult init() {
        return new ValidationResult();
    }
}
