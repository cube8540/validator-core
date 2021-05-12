package io.github.cube8540.validator.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 기본적인 유효성 검사 연산 클래스
 *
 * 만약 등록된 피연산자가 없으면 <code>true</code>를 반환한다.
 *
 * @param <T> 피연산자 타입
 */
public class ValidationOperator<T> implements Operator<T> {

    private final OperatorType operatorType;
    private final List<Validatable<T>> operands = new ArrayList<>();

    public ValidationOperator(OperatorType operatorType) {
        this(operatorType, Collections.emptyList());
    }

    @SafeVarargs
    public ValidationOperator(OperatorType operatorType, Validatable<T> ...operands) {
        this(operatorType, Arrays.asList(operands));
    }

    public ValidationOperator(OperatorType operatorType, List<Validatable<T>> operands) {
        this.operatorType = operatorType;
        this.operands.addAll(operands);
    }

    public ValidationOperator<T> addOperand(Validatable<T> operand) {
        this.operands.add(operand);
        return this;
    }

    @Override
    public OperatorType getType() {
        return operatorType;
    }

    @Override
    public List<Validatable<T>> getOperands() {
        return operands;
    }

    @Override
    public boolean isValid(T target) {
        if (operands.size() == 0) {
            return true;
        }
        if (operatorType.equals(OperatorType.AND) || operatorType.equals(OperatorType.NAND)) {
            boolean result = and(target);
            return operatorType.equals(OperatorType.AND) == result;
        } else if (operatorType.equals(OperatorType.OR) || operatorType.equals(OperatorType.NOR)) {
            return operatorType.equals(OperatorType.OR) == or(target);
        } else {
            return false;
        }
    }

    private boolean and(T target) {
        return operands.stream().allMatch(it -> it.isValid(target));
    }

    private boolean or(T target) {
        for (Validatable<T> operand: operands) {
            if (operand.isValid(target)) {
                return true;
            }
        }
        return false;
    }
}
