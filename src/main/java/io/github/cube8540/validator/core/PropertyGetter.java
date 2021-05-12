package io.github.cube8540.validator.core;

/**
 * 원하는 프로퍼티를 얻어 올 수 있는 인터페이스
 */
public interface PropertyGetter {

    /**
     * 매개변수로 받은 key를 통해 프로퍼티를 얻어온다.
     *
     * @param key 프로퍼티를 얻어올 키
     * @return 프로퍼티
     */
    String resolveProperty(String key);
}
