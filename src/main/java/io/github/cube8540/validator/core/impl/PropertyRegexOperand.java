package io.github.cube8540.validator.core.impl;

import io.github.cube8540.validator.core.PropertyGetter;
import io.github.cube8540.validator.core.Validatable;

import java.util.regex.Pattern;

public class PropertyRegexOperand implements Validatable<PropertyGetter> {

    private final String propertyKey;
    private final Pattern pattern;

    public PropertyRegexOperand(String propertyKey, Pattern pattern) {
        this.propertyKey = propertyKey;
        this.pattern = pattern;
    }

    @Override
    public boolean isValid(PropertyGetter target) {
        return pattern.matcher(target.resolveProperty(propertyKey)).matches();
    }
}
