package cube8540.validator.core;

public interface ValidationRule<T> {

    boolean isValid(T target);

    ValidationError error();

}
