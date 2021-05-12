package io.github.cube8540.validator.core;

import io.github.cube8540.validator.core.exception.ValidateException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationResultTest {

    @Test
    void createObjectErrorIsNotNull() {
        ValidationResult result = ValidationResult.init();

        assertNotNull(result.getErrors());
        assertFalse(result.hasError());
    }

    @Test
    void addError() {
        ValidationResult result = ValidationResult.init();
        ValidationError error = new ValidationError("TEST_PROPERTY", "TEST_MESSAGE");

        ValidationResult addError = result.registerError(error);
        assertEquals(addError, result);
        assertTrue(result.getErrors().contains(error));
    }

    @Test
    void hasError() {
        ValidationResult hasError = ValidationResult
                .init().registerError(new ValidationError("TEST_PROPERTY", "TEST_MESSAGE"));
        assertTrue(hasError.hasError());
    }

    @Test
    void hasErrorThrowException() {
        ValidationResult hasError = ValidationResult.init();
        ValidationError error = new ValidationError("TEST_PROPERTY", "TEST_MESSAGE");

        ValidateException exception = getErrorThrowsException(hasError.registerError(error));
        assertNotNull(exception);
        assertTrue(exception.getErrors().contains(error));
    }

    @Test
    void notHasErrorThrowException() {
        ValidationResult result = ValidationResult.init();

        ValidateException exception = getErrorThrowsException(result);
        assertNull(exception);
    }

    private ValidateException getErrorThrowsException(ValidationResult result) {
        ValidateException exception = null;
        try {
            result.hasErrorThrows(ValidateException::new);
        } catch (ValidateException e) {
            exception = e;
        }
        return exception;
    }

}
