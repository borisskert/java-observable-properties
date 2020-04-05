package de.borisskert.observableproperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class OptionalLongPropertyTest {

    private OptionalProperty<Long> emptyProperty;
    private OptionalProperty<Long> oneTwoThreeProperty;

    @BeforeEach
    public void setup() throws Exception {
        emptyProperty = new OptionalLongProperty();
        oneTwoThreeProperty = new OptionalLongProperty(123L);
    }

    @Test
    public void shouldProvideValue() throws Exception {
        assertThat(oneTwoThreeProperty.get(), is(equalTo(123L)));
        assertThat(emptyProperty.get(), is(nullValue()));
    }

    @Test
    public void shouldAllowChangingToNullValue() throws Exception {
        oneTwoThreeProperty.set(null);
        assertThat(oneTwoThreeProperty.get(), is(nullValue()));
    }
}
