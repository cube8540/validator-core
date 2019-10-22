package cube8540.validator.core;

import java.util.Objects;

/**
 * 유효성 검사기
 *
 * @param <T> 유효성을 검사할 객체의 타입
 */
public class Validator<T> {

    private final T target;
    private final ValidationResult result;

    private Validator(T target) {
        this.target = Objects.requireNonNull(target);
        this.result = ValidationResult.init();
    }

    /**
     * 유효성 검사 규칙을 받아 유효성을 검사하고 유효하지 않을시
     * 인자로 받은 유효성 검사 규칙의 에러 메시지를 검사 결과에 저장한다.
     *
     * @param registerRule 유효성 검사 규칙
     */
    public Validator<T> registerRule(ValidationRule<T> registerRule) {
        if (!registerRule.isValid(target)) {
            this.result.registerError(registerRule.error());
        }
        return this;
    }

    /**
     * 유효성을 확인할 객체를 반환한다.
     *
     * @return 유효성을 확인할 객체
     */
    public T getTarget() {
        return target;
    }

    /**
     * 현재까지 진행된 유효성 검사 결과를 반환한다.
     *
     * @return 유효성 검사 결과
     */
    public ValidationResult getResult() {
        return result;
    }

    /**
     * 인자로 받은 객체의 유효성을 검사하는 검사기를 인스턴스화 하여 반환한다.
     *
     * @param object 유효성을 검사할 객체
     * @param <T> 유효성을 검사할 객체의 타입
     * @return 인자로 받은 객체의 유효성 검사기
     */
    public static <T> Validator<T> of(T object) {
        return new Validator<>(object);
    }
}
