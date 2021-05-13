package io.github.cube8540.validator.core;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatorTest {

    private static final ValidationError ERROR = new ValidationError("TEST_PROPERTY", "TEST_VALUE");

    @Test
    void createObject() {
        Object testObject = Mockito.mock(Object.class);
        Validator<Object> validator = Validator.of(testObject);

        assertEquals(validator.getTarget(), testObject);
        assertFalse(validator.getResult().hasError());
    }

    @Test
    @SuppressWarnings("unchecked")
    void registerRuleIsValidMethodReturnTrue() {
        Object testObject = Mockito.mock(Object.class);
        ValidationRule<Object> rule = Mockito.mock(ValidationRule.class);
        Validator<Object> validator = Validator.of(testObject);

        Mockito.when(rule.isValid(testObject)).thenReturn(Boolean.TRUE);

        Validator<Object> hasRuleValidator = validator.registerRule(rule);
        assertEquals(hasRuleValidator, validator);
        assertFalse(hasRuleValidator.getResult().hasError());
    }

    @Test
    @SuppressWarnings("unchecked")
    void registerRuleIsValidMethodReturnFalse() {
        Object testObject = Mockito.mock(Object.class);
        ValidationRule<Object> rule = Mockito.mock(ValidationRule.class);
        Validator<Object> validator = Validator.of(testObject);

        Mockito.when(rule.isValid(testObject)).thenReturn(Boolean.FALSE);
        Mockito.when(rule.error()).thenReturn(ERROR);

        Validator<Object> hasRuleValidator = validator.registerRule(rule);
        assertEquals(hasRuleValidator, validator);
        assertTrue(hasRuleValidator.getResult().hasError());
        assertTrue(hasRuleValidator.getResult().getErrors().contains(ERROR));
    }

}
