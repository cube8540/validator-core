package cube8540.validator.core;

/**
 * 유효성 검사가 가능하다는 것을 나타내는 인터페이스
 *
 * @param <T> 유효성을 검사할 객체의 타입
 */
public interface Validatable<T> {

    /**
     * 객체의 유효성을 검사한다. 객체가 유효할 경우 <code>true</code>
     * 유효하지 않을 경우 <code>false</code>를 반환한다.
     *
     * @param target 유효성을 검사할 객체
     * @return 유효한 객체인 경우 <code>true</code> 아닐 경우 <code>false</code>
     */
    boolean isValid(T target);

}
