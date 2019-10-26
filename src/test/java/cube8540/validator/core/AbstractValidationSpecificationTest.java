package cube8540.validator.core;

import lombok.Value;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class AbstractValidationSpecificationTest {

    @Test
    public void and() {
        TestObject test = new TestObject("TEST");
        AbstractValidationSpecification<TestObject> trueLeftSpecific = implement(true);
        AbstractValidationSpecification<TestObject> falseLeftSpecific = implement(false);

        assertFalse(falseLeftSpecific.and(falseLeftSpecific).isValid(test));
        assertFalse(falseLeftSpecific.and(trueLeftSpecific).isValid(test));
        assertFalse(trueLeftSpecific.and(falseLeftSpecific).isValid(test));
        assertTrue(trueLeftSpecific.and(trueLeftSpecific).isValid(test));
    }

    @Test
    public void or() {
        TestObject testObject = new TestObject("TEST");
        AbstractValidationSpecification<TestObject> trueLeftSpecific = implement(true);
        AbstractValidationSpecification<TestObject> falseLeftSpecific = implement(false);

        assertFalse(falseLeftSpecific.or(falseLeftSpecific).isValid(testObject));
        assertTrue(falseLeftSpecific.or(trueLeftSpecific).isValid(testObject));
        assertTrue(trueLeftSpecific.or(falseLeftSpecific).isValid(testObject));
        assertTrue(trueLeftSpecific.or(trueLeftSpecific).isValid(testObject));
    }

    @Test
    public void not() {
        TestObject testObject = new TestObject("TEST");
        AbstractValidationSpecification<TestObject> trueSpecific = implement(true);
        AbstractValidationSpecification<TestObject> falseSpecific = implement(false);

        assertTrue(falseSpecific.not().isValid(testObject));
        assertFalse(trueSpecific.not().isValid(testObject));
    }

    @Test
    public void composite() {
        TestObject testObject = new TestObject("TEST");
        ValidationSpecification<Object> spec1 =  implement(true).and(implement(false)).or(implement(true));
        ValidationSpecification<Object> spec2 = implement(false).or(implement(true).or(implement(false)));
        ValidationSpecification<Object> spec3 = implement(true).and(implement(true).not());

        assertTrue(spec1.isValid(testObject));
        assertTrue(spec2.isValid(testObject));
        assertFalse(spec3.isValid(testObject));
    }

    private <T> AbstractValidationSpecification<T> implement(boolean isValid) {
        return new AbstractValidationSpecification<T>() {
            @Override
            public boolean isValid(T target) {
                return isValid;
            }
        };
    }

    @Value
    private static final class TestObject {
        private String value;
    }

}
