package com.github.borisskert;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


class SimpleObjectPropertyTest {

    private Property<TestObject> emptyProperty;
    private Property<TestObject> property;

    private TestObject abcObject;
    private TestObject xyzObject;

    @BeforeEach
    public void setup() throws Exception {
        emptyProperty = new SimpleObjectProperty<>();

        abcObject = new TestObject("abc");
        property = new SimpleObjectProperty<>(abcObject);

        xyzObject = new TestObject("xyz");
    }

    @Test
    public void shouldProvideNullValueIfEmpty() throws Exception {
        assertThat(emptyProperty.get(), is(nullValue()));
    }

    @Test
    public void shouldProvideValue() throws Exception {
        assertThat(property.get(), is(equalTo(abcObject)));
    }

    @Test
    public void shouldProvideModifiedValueIfWasEmpty() throws Exception {
        emptyProperty.set(abcObject);
        assertThat(emptyProperty.get(), is(equalTo(abcObject)));
    }

    @Test
    public void shouldProvideModifiedValue() throws Exception {
        property.set(xyzObject);
        assertThat(property.get(), is(equalTo(xyzObject)));
    }

    @Test
    public void shouldProvideChangesToAddedListener() throws Exception {
        TestChangeListener<TestObject> listener = new TestChangeListener<>();
        property.addListener(listener);

        property.set(xyzObject);

        assertThat(listener.calls, is(equalTo(1)));
        assertThat(listener.property, is(sameInstance(property)));
        assertThat(listener.oldValue, is(sameInstance(abcObject)));
        assertThat(listener.newValue, is(sameInstance(xyzObject)));
    }

    @Test
    public void shouldProvideChangesOfEmptyPropertyToAddedListener() throws Exception {
        TestChangeListener<TestObject> listener = new TestChangeListener<>();
        emptyProperty.addListener(listener);

        emptyProperty.set(abcObject);

        assertThat(listener.calls, is(equalTo(1)));
        assertThat(listener.property, is(sameInstance(emptyProperty)));
        assertThat(listener.oldValue, is(nullValue()));
        assertThat(listener.newValue, is(sameInstance(abcObject)));
    }

    @Test
    public void shouldNotProvideChangesToAddedListenerIfNoActualChanges() throws Exception {
        TestChangeListener<TestObject> listener = new TestChangeListener<>();
        property.addListener(listener);

        property.set(abcObject);

        assertThat(listener.calls, is(equalTo(0)));
    }

    @Test
    public void shouldProvideTwoChangesToAddedListener() throws Exception {
        TestChangeListener<TestObject> listener = new TestChangeListener<>();
        property.addListener(listener);

        property.set(xyzObject);
        property.set(abcObject);

        assertThat(listener.calls, is(equalTo(2)));
        assertThat(listener.property, is(sameInstance(property)));
        assertThat(listener.oldValue, is(sameInstance(xyzObject)));
        assertThat(listener.newValue, is(sameInstance(abcObject)));
    }

    @Test
    public void shouldNotProvideChangesToRemovedListener() throws Exception {
        TestChangeListener<TestObject> listener = new TestChangeListener<>();
        property.addListener(listener);
        property.removeListener(listener);

        property.set(xyzObject);

        assertThat(listener.calls, is(equalTo(0)));
    }

    @Test
    public void shouldProvideChangesToTwoAddedListeners() throws Exception {
        TestChangeListener<TestObject> listenerOne = new TestChangeListener<>();
        TestChangeListener<TestObject> listenerTwo = new TestChangeListener<>();

        property.addListener(listenerOne);
        property.addListener(listenerTwo);

        property.set(xyzObject);

        assertThat(listenerOne.calls, is(equalTo(1)));
        assertThat(listenerOne.property, is(sameInstance(property)));
        assertThat(listenerOne.oldValue, is(sameInstance(abcObject)));
        assertThat(listenerOne.newValue, is(sameInstance(xyzObject)));

        assertThat(listenerTwo.calls, is(equalTo(1)));
        assertThat(listenerTwo.property, is(sameInstance(property)));
        assertThat(listenerTwo.oldValue, is(sameInstance(abcObject)));
        assertThat(listenerTwo.newValue, is(sameInstance(xyzObject)));
    }
}
