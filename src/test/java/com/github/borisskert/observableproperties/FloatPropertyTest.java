package com.github.borisskert.observableproperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.*;

class FloatPropertyTest {

    private Property<Float> oneDotTwoProperty;

    @BeforeEach
    public void setup() throws Exception {
        oneDotTwoProperty = new FloatProperty(1.2f);
    }

    @Test
    public void shouldProvideValue() throws Exception {
        assertThat(oneDotTwoProperty.get(), is(equalTo(1.2f)));
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