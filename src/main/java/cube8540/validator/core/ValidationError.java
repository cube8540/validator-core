package cube8540.validator.core;

import lombok.Value;

import java.util.Objects;

@Value
public class ValidationError {

    private String property;
    private String message;

    public ValidationError(String property, String message) {
        this.property = Objects.requireNonNull(property);
        this.message = Objects.requireNonNull(message);
    }

}
