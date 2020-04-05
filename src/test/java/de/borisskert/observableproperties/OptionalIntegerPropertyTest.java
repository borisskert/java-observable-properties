package de.borisskert.observableproperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class OptionalIntegerPropertyTest {

    private OptionalProperty<Integer> emptyProperty;
    private OptionalProperty<Integer> oneTwoThreeProperty;

    @BeforeEach
    public void setup() throws Exception {
        emptyProperty = new OptionalIntegerProperty();
        oneTwoThreeProperty = new OptionalIntegerProperty(123);
    }

    @Test
    public void shouldProvideValue() throws Exception {
        assertThat(oneTwoThreeProperty.get(), is(equalTo(123)));
        assertThat(emptyProperty.get(), is(nullValue()));
    }

    @Test
    public void shouldAllowChangingToNullValue() throws Exception {
        oneTwoThreeProperty.set(null);
        assertThat(oneTwoThreeProperty.get(), is(nullValue()));
    }
}
