package de.borisskert.observableproperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;

class BooleanPropertyTest {
    private Property<Boolean> trueProperty;
    private Property<Boolean> falseProperty;

    @BeforeEach
    public void setup() throws Exception {
        trueProperty = new BooleanProperty(true);
        falseProperty = new BooleanProperty(false);
    }

    @Test
    public void shouldProvideValue() throws Exception {
        assertThat(trueProperty.get(), is(equalTo(true)));
    }

    @Test
    public void shouldNotAllowChangingValueToNull() throws Exception {
        try {
            trueProperty.set(null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), is(equalTo("Parameter 'value' must not be null")));
        }

        try {
            falseProperty.set(null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), is(equalTo("Parameter 'value' must not be null")));
        }
    }
}
