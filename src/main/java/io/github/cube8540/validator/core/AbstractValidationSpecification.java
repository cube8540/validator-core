package io.github.cube8540.validator.core;

/**
 * 유효성 명세서 추상 클래스로 {@link ValidationSpecification}를 구현하고 있으며
 * 이 클래스를 상속 받아 {@link #isValid(Object)} 메소드를 구현한다.
 *
 * {@link #and(ValidationSpecification)}, {@link #or(ValidationSpecification)}, {@link #not()} 메소드에는
 * final 키워드가 붙어 오버라이딩 할 수 없다.
 *
 * @param <T> 유효성을 검사할 객체의 타입
 */
public abstract class AbstractValidationSpecification<T> implements ValidationSpecification<T> {

    @Override
    public final ValidationSpecification<T> and(ValidationSpecification<T> specification) {
        return new And<>(this, specification);
    }

    @Override
    public final ValidationSpecification<T> or(ValidationSpecification<T> specification) {
        return new Or<>(this, specification);
    }

    @Override
    public final ValidationSpecification<T> not() {
        return new Not<>(this);
    }

    private static class And<N> extends AbstractValidationSpecification<N> {

        private final ValidationSpecification<N> left;
        private final ValidationSpecification<N> right;

        private And(ValidationSpecification<N> left, ValidationSpecification<N> right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean isValid(N target) {
            return left.isValid(target) && right.isValid(target);
        }
    }

    private static class Or<N> extends AbstractValidationSpecification<N> {

        private final ValidationSpecification<N> left;
        private final ValidationSpecification<N> right;

        private Or(ValidationSpecification<N> left, ValidationSpecification<N> right) {
            this.left = left;
            this.right = right;
        }

        @Override
        public boolean isValid(N target) {
            return left.isValid(target) || right.isValid(target);
        }
    }

    private static class Not<N> extends AbstractValidationSpecification<N> {

        private final ValidationSpecification<N> specification;

        private Not(ValidationSpecification<N> specification) {
            this.specification = specification;
        }

        @Override
        public boolean isValid(N target) {
            return !specification.isValid(target);
        }
    }
}
