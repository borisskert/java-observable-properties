package de.borisskert.observableproperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class OptionalBooleanPropertyTest {

    private OptionalProperty<Boolean> emptyProperty;
    private OptionalProperty<Boolean> trueProperty;
    private OptionalProperty<Boolean> falseProperty;

    @BeforeEach
    public void setup() throws Exception {
        emptyProperty = new OptionalBooleanProperty();
        trueProperty = new OptionalBooleanProperty(true);
        falseProperty = new OptionalBooleanProperty(false);
    }

    @Test
    public void shouldProvideValue() throws Exception {
        assertThat(trueProperty.get(), is(equalTo(true)));
        assertThat(falseProperty.get(), is(equalTo(false)));
        assertThat(emptyProperty.get(), is(nullValue()));
    }

    @Test
    public void shouldAllowChangingToNullValue() throws Exception {
        trueProperty.set(null);
        assertThat(trueProperty.get(), is(nullValue()));

        falseProperty.set(null);
        assertThat(falseProperty.get(), is(nullValue()));
    }
}
