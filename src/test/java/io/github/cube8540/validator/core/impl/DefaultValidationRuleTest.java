package io.github.cube8540.validator.core.impl;

import io.github.cube8540.validator.core.ValidationError;
import io.github.cube8540.validator.core.ValidationSpecification;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class DefaultValidationRuleTest {

    private static final String PROPERTY = "TEST_PROPERTY";
    private static final String MESSAGE = "TEST_VALUE";

    @Test
    @SuppressWarnings("unchecked")
    void isValid() {
        Object testObject = Mockito.mock(Object.class);
        ValidationSpecification<Object> falseSpec = Mockito.mock(ValidationSpecification.class);
        ValidationSpecification<Object> trueSpec = Mockito.mock(ValidationSpecification.class);
        DefaultValidationRule<Object> falseRule = DefaultValidationRule.withSpecification(falseSpec).create(PROPERTY, MESSAGE);
        DefaultValidationRule<Object> trueRule = DefaultValidationRule.withSpecification(trueSpec).create(PROPERTY, MESSAGE);

        Mockito.when(trueSpec.isValid(testObject)).thenReturn(Boolean.TRUE);
        Mockito.when(falseSpec.isValid(testObject)).thenReturn(Boolean.FALSE);

        assertTrue(trueRule.isValid(testObject));
        assertFalse(falseRule.isValid(testObject));
    }

    @Test
    @SuppressWarnings("unchecked")
    void error() {
        ValidationSpecification<Object> spec = Mockito.mock(ValidationSpecification.class);
        DefaultValidationRule<Object> rule = DefaultValidationRule.withSpecification(spec).create(PROPERTY, MESSAGE);
        ValidationError error = rule.error();

        assertEquals(error.getProperty(), PROPERTY);
        assertEquals(error.getMessage(), MESSAGE);
    }

}
