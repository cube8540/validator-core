package io.github.cube8540.validator.core;

import java.util.List;

/**
 * 유효성 검사를 하기 위한 연산자 인터페이스
 *
 * @param <T> 피연산자 타입
 */
public interface Operator<T> extends Validatable<T> {

    /**
     * 연산자 타입을 반환한다.
     *
     * @return 연산자 타입
     */
    OperatorType getType();

    /**
     * 모든 피연산자들을 반환한다.
     *
     * @return 피연산자 리스트
     */
    List<Validatable<T>> getOperands();

    /**
     * 연사자 타입 enum
     */
    enum OperatorType {
        AND, OR, NAND, NOR
    }
}
