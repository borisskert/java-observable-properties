package de.borisskert.observableproperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class OptionalFloatPropertyTest {

    private OptionalProperty<Float> emptyProperty;
    private OptionalProperty<Float> oneDotTwoProperty;

    @BeforeEach
    public void setup() throws Exception {
        emptyProperty = new OptionalFloatProperty();
        oneDotTwoProperty = new OptionalFloatProperty(1.2f);
    }

    @Test
    public void shouldProvideValue() throws Exception {
        assertThat(oneDotTwoProperty.get(), is(equalTo(1.2f)));
        assertThat(emptyProperty.get(), is(nullValue()));
    }

    @Test
    public void shouldAllowChangingToNullValue() throws Exception {
        oneDotTwoProperty.set(null);
        assertThat(oneDotTwoProperty.get(), is(nullValue()));
    }
}
