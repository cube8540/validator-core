package cube8540.validator.core;

/**
 * 유효성 규칙 정의 인터페이스
 *
 * @param <T> 유효성을 검사할 객체의 타입
 */
public interface ValidationRule<T> extends Validatable<T> {

    /**
     * 객체가 유효하지 않을 경우 호출되어 유효성 검사에 대한 에러 메시지를 반환한다.
     *
     * @return 유효성 검사 에러 메시지
     */
    ValidationError error();

}
