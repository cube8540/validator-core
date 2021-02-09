package io.github.cube8540.validator.core;

import io.github.cube8540.validator.core.exception.ValidateException;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertNull;

public class ValidationResultTest {

    @Test
    public void createObjectErrorIsNotNull() {
        ValidationResult result = ValidationResult.init();

        assertNotNull(result.getErrors());
        assertFalse(result.hasError());
    }

    @Test
    public void addError() {
        ValidationResult result = ValidationResult.init();
        ValidationError error = new ValidationError("TEST_PROPERTY", "TEST_MESSAGE");

        ValidationResult addError = result.registerError(error);
        assertEquals(addError, result);
        assertTrue(result.getErrors().contains(error));
    }

    @Test
    public void hasError() {
        ValidationResult hasError = ValidationResult
                .init().registerError(new ValidationError("TEST_PROPERTY", "TEST_MESSAGE"));
        assertTrue(hasError.hasError());
    }

    @Test
    public void hasErrorThrowException() {
        ValidationResult hasError = ValidationResult.init();
        ValidationError error = new ValidationError("TEST_PROPERTY", "TEST_MESSAGE");

        ValidateException exception = getErrorThrowsException(hasError.registerError(error));
        assertNotNull(exception);
        assertTrue(exception.getErrors().contains(error));
    }

    @Test
    public void notHasErrorThrowException() {
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
