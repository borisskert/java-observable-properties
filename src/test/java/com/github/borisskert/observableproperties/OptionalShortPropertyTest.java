package com.github.borisskert.observableproperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class OptionalShortPropertyTest {

    private OptionalProperty<Short> emptyProperty;
    private OptionalProperty<Short> oneTwoThreeProperty;
    private short value;

    @BeforeEach
    public void setup() throws Exception {
        emptyProperty = new OptionalShortProperty();

        value = 123;
        oneTwoThreeProperty = new OptionalShortProperty(value);
    }

    @Test
    public void shouldProvideValue() throws Exception {
        assertThat(oneTwoThreeProperty.get(), is(equalTo(value)));
        assertThat(emptyProperty.get(), is(nullValue()));
    }

    @Test
    public void shouldAllowChangingToNullValue() throws Exception {
        oneTwoThreeProperty.set(null);
        assertThat(oneTwoThreeProperty.get(), is(nullValue()));
    }
}