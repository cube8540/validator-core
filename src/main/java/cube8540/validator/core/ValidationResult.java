package cube8540.validator.core;

import cube8540.validator.core.exception.ValidateException;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

/**
 * 유효성 검사 결과
 */
@ToString
@EqualsAndHashCode
public class ValidationResult {

    private final List<ValidationError> errors;

    private ValidationResult() {
        this.errors = new ArrayList<>();
    }

    /**
     * 현재 등록된 유효성 검사 에러 메시지를 반환한다.
     *
     * @return 등록된 유효성 검사 에러 메시지
     */
    public List<ValidationError> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    /**
     * 유효성 검사 에러 메시지를 등록한다. 하나라도 등록되어 있을 경우
     * hasError 메소드는 <code>true</code>를 반환한다.
     *
     * @param error 에러 메시지
     */
    public ValidationResult registerError(ValidationError error) {
        this.errors.add(error);
        return this;
    }

    /**
     * 현재 등록된 유효성 검사 에러 메시지가 있는지 여부를 반환한다.
     *
     * @return 실패한 결과가 있을 경우 <code>true</code> 없을 경우 <code>false</code>
     */
    public boolean hasError() {
        return !errors.isEmpty();
    }

    /**
     * 현재 등록된 유효성 검사 에러 메시지가 있을 경우 인자로 넘긴 함수를 통해
     * {@link ValidateException}을 발생시킨다.
     *
     * @param exceptionFunction 실패한 결과가 있을 경우 발생할 예외 객체의 생성 함수
     */
    public void hasErrorThrows(Function<List<ValidationError>, ValidateException> exceptionFunction) {
        if (hasError()) {
            throw exceptionFunction.apply(errors);
        }
    }

    /**
     * 유효성 검사 결과 클래스를 인스턴스화 하여 반환한다.
     *
     * @return 유효성 검사 결과 객체
     */
    static ValidationResult init() {
        return new ValidationResult();
    }
}
