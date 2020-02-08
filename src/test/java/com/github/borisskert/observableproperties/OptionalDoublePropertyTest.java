package com.github.borisskert.observableproperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class OptionalDoublePropertyTest {

    private OptionalProperty<Double> emptyProperty;
    private OptionalProperty<Double> oneDotTwoProperty;

    @BeforeEach
    public void setup() throws Exception {
        emptyProperty = new OptionalDoubleProperty();
        oneDotTwoProperty = new OptionalDoubleProperty(1.2);
    }

    @Test
    public void shouldProvideValue() throws Exception {
        assertThat(oneDotTwoProperty.get(), is(equalTo(1.2)));
        assertThat(emptyProperty.get(), is(nullValue()));
    }

    @Test
    public void shouldAllowChangingToNullValue() throws Exception {
        oneDotTwoProperty.set(null);
        assertThat(oneDotTwoProperty.get(), is(nullValue()));
    }
}
