package io.github.cube8540.validator.core.impl;

import io.github.cube8540.validator.core.AbstractValidationSpecification;

import java.util.function.Function;
import java.util.regex.Pattern;

public class RegexValidationSpecification<T> extends AbstractValidationSpecification<T> {

    private final Function<T, String> propertyFunction;
    private final Pattern pattern;

    public RegexValidationSpecification(Function<T, String> propertyFunction, Pattern pattern) {
        this.propertyFunction = propertyFunction;
        this.pattern = pattern;
    }

    @Override
    public boolean isValid(T target) {
        return pattern.matcher(propertyFunction.apply(target)).matches();
    }

    public Function<T, String> getPropertyFunction() {
        return propertyFunction;
    }

    public Pattern getPattern() {
        return pattern;
    }
}
