package cube8540.validator.core;

import lombok.Value;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class ValidatorTest {

    private static final ValidationError ERROR = new ValidationError("TEST_PROPERTY", "TEST_VALUE");

    @Test
    public void createObject() {
        TestObject testObject = new TestObject("TEST_VALUE");
        Validator<TestObject> validator = Validator.of(testObject);

        assertEquals(validator.getTarget(), testObject);
        assertFalse(validator.getResult().hasError());
    }

    @Test
    public void registerRuleIsValidMethodReturnTrue() {
        TestObject testObject = new TestObject("TEST_VALUE");
        Validator<TestObject> validator = Validator.of(testObject);

        Validator<TestObject> hasRuleValidator = validator.registerRule(new TestMustReturnTrueValidationRule());
        assertEquals(hasRuleValidator, validator);
        assertFalse(hasRuleValidator.getResult().hasError());
    }

    @Test
    public void registerRuleIsValidMethodReturnFalse() {
        TestObject testObject = new TestObject("TEST_VALUE");
        Validator<TestObject> validator = Validator.of(testObject);

        Validator<TestObject> hasRuleValidator = validator.registerRule(new TestMustReturnFalseValidationRule());
        assertEquals(hasRuleValidator, validator);
        assertTrue(hasRuleValidator.getResult().hasError());
        assertTrue(hasRuleValidator.getResult().getErrors().contains(ERROR));
    }

    @Value
    private static final class TestObject {
        private String value;
    }

    private static final class TestMustReturnTrueValidationRule implements ValidationRule<TestObject> {
        @Override
        public boolean isValid(TestObject target) {
            return true;
        }
        @Override
        public ValidationError error() {
            throw new UnsupportedOperationException();
        }
    }

    private static final class TestMustReturnFalseValidationRule implements ValidationRule<TestObject> {
        @Override
        public boolean isValid(TestObject target) {
            return false;
        }
        @Override
        public ValidationError error() {
            return ERROR;
        }
    }

}
