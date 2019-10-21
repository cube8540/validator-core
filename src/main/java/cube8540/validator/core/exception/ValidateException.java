package cube8540.validator.core.exception;

import cube8540.validator.core.ValidationError;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class ValidateException extends RuntimeException {

    private Collection<ValidationError> errors;

    public ValidateException(ValidationError... errors) {
        this(Arrays.asList(errors));
    }

    public ValidateException(Collection<ValidationError> errors) {
        this.errors = Collections.unmodifiableCollection(errors);
    }

    @Override
    public String getMessage() {
        return errors.toString();
    }

    public Collection<ValidationError> getErrors() {
        return errors;
    }
}
