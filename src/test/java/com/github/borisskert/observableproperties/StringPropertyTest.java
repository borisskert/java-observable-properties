package com.github.borisskert.observableproperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.fail;

class StringPropertyTest {

    private Property<String> abcProperty;

    @BeforeEach
    public void setup() throws Exception {
        abcProperty = new StringProperty("abc");
    }

    @Test
    public void shouldProvideValue() throws Exception {
        assertThat(abcProperty.get(), is(equalTo("abc")));
    }

    @Test
    public void shouldNotAllowChangingValueToNull() throws Exception {
        try {
            abcProperty.set(null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), is(equalTo("Parameter 'value' must not be null")));
        }
    }

    @Test
    public void shouldNotAllowInstanceCreationWithNull() throws Exception {
        try {
            new StringProperty(null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), is(equalTo("Parameter 'value' must not be null")));
        }
    }
}
