package de.borisskert.observableproperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

class OptionalStringPropertyTest {

    private OptionalProperty<String> emptyProperty;
    private OptionalProperty<String> abcProperty;

    @BeforeEach
    public void setup() throws Exception {
        emptyProperty = new OptionalStringProperty();
        abcProperty = new OptionalStringProperty("abc");
    }

    @Test
    public void shouldProvideValue() throws Exception {
        assertThat(abcProperty.get(), is(equalTo("abc")));
        assertThat(emptyProperty.get(), is(nullValue()));
    }

    @Test
    public void shouldAllowChangingToNullValue() throws Exception {
        abcProperty.set(null);
        assertThat(abcProperty.get(), is(nullValue()));
    }
}
