package cube8540.validator.core.impl;

import cube8540.validator.core.ValidationError;
import cube8540.validator.core.ValidationSpecification;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class DefaultValidationRuleTest {

    private static final String PROPERTY = "TEST_PROPERTY";
    private static final String MESSAGE = "TEST_VALUE";

    @Test
    @SuppressWarnings("unchecked")
    public void isValid() {
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
    public void error() {
        ValidationSpecification<Object> spec = Mockito.mock(ValidationSpecification.class);
        DefaultValidationRule<Object> rule = DefaultValidationRule.withSpecification(spec).create(PROPERTY, MESSAGE);
        ValidationError error = rule.error();

        assertEquals(error.getProperty(), PROPERTY);
        assertEquals(error.getMessage(), MESSAGE);
    }

}
