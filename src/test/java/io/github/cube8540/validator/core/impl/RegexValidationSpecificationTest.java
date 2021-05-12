package io.github.cube8540.validator.core.impl;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class RegexValidationSpecificationTest {
    private static final Pattern pattern = Mockito.mock(Pattern.class);

    private static final Function<Object, String> getter = (v) -> "value";


    private final RegexValidationSpecification<Object> specification = new RegexValidationSpecification<>(getter, pattern);

    @Test
    void isValid() {
        Matcher matcher = Mockito.mock(Matcher.class);

        Mockito.when(pattern.matcher("value")).thenReturn(matcher);
        Mockito.when(matcher.matches()).thenReturn(true);

        boolean result = specification.isValid(new Object());
        assertTrue(result);
    }

}