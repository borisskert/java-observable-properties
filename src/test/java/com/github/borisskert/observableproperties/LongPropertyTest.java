package com.github.borisskert.observableproperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

class LongPropertyTest {

    private Property<Long> oneTwoThreeProperty;

    @BeforeEach
    public void setup() throws Exception {
        oneTwoThreeProperty = new LongProperty(123L);
    }

    @Test
    public void shouldProvideValue() throws Exception {
        assertThat(oneTwoThreeProperty.get(), is(equalTo(123L)));
    }

    @Test
    public void shouldNotAllowChangingValueToNull() throws Exception {
        try {
            oneTwoThreeProperty.set(null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), is(equalTo("Parameter 'value' must not be null")));
        }
    }
}