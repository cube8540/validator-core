package io.github.cube8540.validator.core;

import org.junit.Test;

import static junit.framework.TestCase.assertNotNull;

public class ValidationErrorTest {

    @Test
    public void createObjectWithNullThrowNullPointException() {
        NullPointerException exception = null;
        try {
            new ValidationError(null, null);
        } catch (NullPointerException e) {
            exception = e;
        }
        assertNotNull(exception);
    }

}