package cube8540.validator.core.impl;

import cube8540.validator.core.AbstractValidationSpecification;
import cube8540.validator.core.ValidationError;
import cube8540.validator.core.ValidationSpecification;
import lombok.Value;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class DefaultValidationRuleTest {

    @Test
    public void isValid() {
        TestObject testObject = new TestObject("TEST");
        ValidationSpecification<TestObject> falseSpec = new MustReturnFalseValidationSpecification()
                .and(new MustReturnFalseValidationSpecification());
        ValidationSpecification<TestObject> trueSpec = new MustReturnTrueValidationSpecification()
                .and(new MustReturnTrueValidationSpecification());
        DefaultValidationRule<TestObject> falseRule = DefaultValidationRule.withSpecification(falseSpec)
                .create("PROPERTY", "MESSAGE");
        DefaultValidationRule<TestObject> trueRule = DefaultValidationRule.withSpecification(trueSpec)
                .create("PROPERTY", "MESSAGE");

        assertTrue(trueRule.isValid(testObject));
        assertFalse(falseRule.isValid(testObject));
    }

    @Test
    public void error() {
        TestObject testObject = new TestObject("TEST");
        ValidationSpecification<TestObject> spec = new MustReturnTrueValidationSpecification();
        DefaultValidationRule<TestObject> rule = DefaultValidationRule.withSpecification(spec)
                .create("PROPERTY", "MESSAGE");
        ValidationError error = rule.error();

        assertEquals(error.getProperty(), "PROPERTY");
        assertEquals(error.getMessage(), "MESSAGE");
    }

    @Value
    private static final class TestObject {
        private String value;
    }

    private static final class MustReturnTrueValidationSpecification extends AbstractValidationSpecification<TestObject> {
        @Override
        public boolean isValid(TestObject target) {
            return true;
        }
    }

    private static final class MustReturnFalseValidationSpecification extends AbstractValidationSpecification<TestObject> {
        @Override
        public boolean isValid(TestObject target) {
            return false;
        }
    }

}
