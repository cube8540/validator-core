package cube8540.validator.core;

import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertTrue;

public class AbstractValidationSpecificationTest {

    @Test
    public void and() {
        Object testObject = Mockito.mock(Object.class);
        AbstractValidationSpecification<Object> trueLeftSpecific = implement(true);
        AbstractValidationSpecification<Object> falseLeftSpecific = implement(false);

        assertFalse(falseLeftSpecific.and(falseLeftSpecific).isValid(testObject));
        assertFalse(falseLeftSpecific.and(trueLeftSpecific).isValid(testObject));
        assertFalse(trueLeftSpecific.and(falseLeftSpecific).isValid(testObject));
        assertTrue(trueLeftSpecific.and(trueLeftSpecific).isValid(testObject));
    }

    @Test
    public void or() {
        Object testObject = Mockito.mock(Object.class);
        AbstractValidationSpecification<Object> trueLeftSpecific = implement(true);
        AbstractValidationSpecification<Object> falseLeftSpecific = implement(false);

        assertFalse(falseLeftSpecific.or(falseLeftSpecific).isValid(testObject));
        assertTrue(falseLeftSpecific.or(trueLeftSpecific).isValid(testObject));
        assertTrue(trueLeftSpecific.or(falseLeftSpecific).isValid(testObject));
        assertTrue(trueLeftSpecific.or(trueLeftSpecific).isValid(testObject));
    }

    @Test
    public void not() {
        Object testObject = Mockito.mock(Object.class);
        AbstractValidationSpecification<Object> trueSpecific = implement(true);
        AbstractValidationSpecification<Object> falseSpecific = implement(false);

        assertTrue(falseSpecific.not().isValid(testObject));
        assertFalse(trueSpecific.not().isValid(testObject));
    }

    @Test
    public void composite() {
        Object testObject = Mockito.mock(Object.class);
        ValidationSpecification<Object> spec1 =  implement(true).and(implement(false)).or(implement(true));
        ValidationSpecification<Object> spec2 = implement(false).or(implement(true).or(implement(false)));
        ValidationSpecification<Object> spec3 = implement(true).and(implement(true).not());

        assertTrue(spec1.isValid(testObject));
        assertTrue(spec2.isValid(testObject));
        assertFalse(spec3.isValid(testObject));
    }

    private <T> AbstractValidationSpecification<T> implement(boolean isValid) {
        return new AbstractValidationSpecification<>() {
            @Override
            public boolean isValid(T target) {
                return isValid;
            }
        };
    }

}
