package cube8540.validator.core;

/**
 * 유효성 명세서 인터페이스
 *
 * @param <T> 유효성을 검사할 객체의 타입
 */
public interface ValidationSpecification<T> extends Validatable<T> {

    /**
     * 현재 유효성 명세서와 인자로 받은 유효성 명세서를 <code>AND</code> 연산하는
     * 유효성 명세서 인터페이스를 반환한다.
     *
     * @param specification 결과를 비교할 유효성 명세서 인터페이스
     * @return AND 연산 명세서 인터페이스
     */
    ValidationSpecification<T> and(ValidationSpecification<T> specification);

    /**
     * 현재 유효성 명세서와 인자로 받은 유효성 명세서를 <code>OR</code> 연산하는
     * 유효성 명세서 인터페이스를 반환한다.
     *
     * @param specification 결과를 비교할 유효성 명세서 인터페이스
     * @return OR 연산 명세서 인터페이스
     */
    ValidationSpecification<T> or(ValidationSpecification<T> specification);

    /**
     * 현재 유효성 명세서를 <code>NOT</code> 연산하는 유효성 명세서 인터페이스를 반환한다.
     *
     * @return NOT 연산 명세서 인터페이스
     */
    ValidationSpecification<T> not();

}
