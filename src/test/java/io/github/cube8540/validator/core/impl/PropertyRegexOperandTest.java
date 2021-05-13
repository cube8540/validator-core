package io.github.cube8540.validator.core.impl;

import io.github.cube8540.validator.core.PropertyGetter;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PropertyRegexOperandTest {

    private static final String property = "property";
    private static final Pattern pattern = Mockito.mock(Pattern.class);

    private PropertyRegexOperand operand = new PropertyRegexOperand(property, pattern);

    @Test
    void isValid() {
        PropertyGetter getter = Mockito.mock(PropertyGetter.class);

        Matcher matcher = Mockito.mock(Matcher.class);

        Mockito.when(getter.resolveProperty(property)).thenReturn("returnProperty");
        Mockito.when(pattern.matcher("returnProperty")).thenReturn(matcher);
        Mockito.when(matcher.matches()).thenReturn(true);

        boolean result = operand.isValid(getter);
        assertTrue(result);
    }

}