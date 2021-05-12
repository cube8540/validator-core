package io.github.cube8540.validator.core;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.Mockito;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidationOperatorTest {

    @SuppressWarnings("unchecked")
    private static final Validatable<Object> left = Mockito.mock(Validatable.class);

    @SuppressWarnings("unchecked")
    private static final Validatable<Object> right = Mockito.mock(Validatable.class);

    @Test
    void isValidCheckWhenOperandIsEmpty() {
        ValidationOperator<Object> and = new ValidationOperator<>(Operator.OperatorType.AND);
        ValidationOperator<Object> or = new ValidationOperator<>(Operator.OperatorType.OR);
        ValidationOperator<Object> nand = new ValidationOperator<>(Operator.OperatorType.NAND);
        ValidationOperator<Object> nor = new ValidationOperator<>(Operator.OperatorType.NOR);

        assertTrue(and.isValid(null));
        assertTrue(or.isValid(null));
        assertTrue(nand.isValid(null));
        assertTrue(nor.isValid(null));
    }

    @ParameterizedTest
    @MethodSource("resultProvider")
    void isValidCheckAndOperation(Operator.OperatorType operatorType, boolean leftReturn, boolean rightReturn, boolean result) {
        ValidationOperator<Object> validator = new ValidationOperator<>(operatorType, left, right);

        Object target = new Object();
        Mockito.when(left.isValid(target)).thenReturn(leftReturn);
        Mockito.when(right.isValid(target)).thenReturn(rightReturn);

        assertEquals(result, validator.isValid(target));
    }

    private static Stream<Arguments> resultProvider() {
        return Stream.of(
                Arguments.of(Operator.OperatorType.AND, true, true, true),
                Arguments.of(Operator.OperatorType.AND, true, false, false),
                Arguments.of(Operator.OperatorType.AND, false, false, false),

                Arguments.of(Operator.OperatorType.OR, true, true, true),
                Arguments.of(Operator.OperatorType.OR, true, false, true),
                Arguments.of(Operator.OperatorType.OR, false, false, false),

                Arguments.of(Operator.OperatorType.NAND, true, true, false),
                Arguments.of(Operator.OperatorType.NAND, true, false, true),
                Arguments.of(Operator.OperatorType.NAND, false, false, true),

                Arguments.of(Operator.OperatorType.NOR, true, true, false),
                Arguments.of(Operator.OperatorType.NOR, true, false, false),
                Arguments.of(Operator.OperatorType.NOR, false, false, true)
        );
    }
}