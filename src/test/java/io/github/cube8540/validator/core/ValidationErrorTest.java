package io.github.cube8540.validator.core;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class ValidationErrorTest {

    @Test
    void createObjectWithNullThrowNullPointException() {
        NullPointerException exception = null;
        try {
            new ValidationError(null, null);
        } catch (NullPointerException e) {
            exception = e;
        }
        assertNotNull(exception);
    }

}