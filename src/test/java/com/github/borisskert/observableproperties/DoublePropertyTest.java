package com.github.borisskert.observableproperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

class DoublePropertyTest {

    private Property<Double> oneDotTwoProperty;

    @BeforeEach
    public void setup() throws Exception {
        oneDotTwoProperty = new DoubleProperty(1.2);
    }

    @Test
    public void shouldProvideValue() throws Exception {
        assertThat(oneDotTwoProperty.get(), is(equalTo(1.2)));
    }

    @Test
    public void shouldNotAllowChangingValueToNull() throws Exception {
        try {
            oneDotTwoProperty.set(null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), is(equalTo("Parameter 'value' must not be null")));
        }
    }
}
