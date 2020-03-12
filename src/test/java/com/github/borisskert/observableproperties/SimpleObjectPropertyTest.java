package com.github.borisskert.observableproperties;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.fail;


class SimpleObjectPropertyTest {

    private Property<TestObject> property;

    private TestObject abcObject;
    private TestObject xyzObject;

    @BeforeEach
    public void setup() throws Exception {
        abcObject = new TestObject("abc");
        property = new SimpleObjectProperty<>(abcObject);

        xyzObject = new TestObject("xyz");
    }

    @Test
    public void shouldProvideValue() throws Exception {
        assertThat(property.get(), is(equalTo(abcObject)));
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

    @Test
    public void shouldNotAllowToInitializeWithNullValue() throws Exception {
        try {
            new SimpleObjectProperty<>(null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), is(equalTo("Parameter 'value' must not be null")));
        }
    }

    @Test
    public void shouldNotAllowToSetNullValue() throws Exception {
        try {
            property.set(null);
            fail("Should throw NullPointerException");
        } catch (NullPointerException e) {
            assertThat(e.getMessage(), is(equalTo("Parameter 'value' must not be null")));
        }
    }

    @Test
    public void shouldEqualPropertyWithSameValue() throws Exception {
        Property<TestObject> anotherProperty = new SimpleObjectProperty<>(abcObject);

        assertThat(anotherProperty, is(equalTo(property)));
        assertThat(property, is(equalTo(anotherProperty)));
    }

    @Test
    public void shouldProvideSameHashCodeAsPropertyWithSameValue() throws Exception {
        Property<TestObject> anotherProperty = new SimpleObjectProperty<>(abcObject);

        assertThat(anotherProperty.hashCode(), is(equalTo(property.hashCode())));
    }

    @Test
    public void shouldEqualOptionalPropertyWithEqualValue() throws Exception {
        Property<TestObject> anotherProperty = new SimpleOptionalProperty<>(new TestObject("abc"));

        assertThat(property, is(equalTo(anotherProperty)));
    }

    @Test
    public void shouldProvideSameHashCodeLikeOptionalPropertyWithEqualValue() throws Exception {
        Property<TestObject> anotherProperty = new SimpleOptionalProperty<>(new TestObject("abc"));

        assertThat(anotherProperty.hashCode(), is(equalTo(property.hashCode())));
    }

    @Test
    public void shouldEqualPropertyWithEqualValue() throws Exception {
        Property<TestObject> anotherProperty = new SimpleObjectProperty<>(new TestObject("abc"));

        assertThat(anotherProperty, is(equalTo(property)));
        assertThat(property, is(equalTo(anotherProperty)));
    }

    @Test
    public void shouldProvideSameHashCodeAsPropertyWithEqualValue() throws Exception {
        Property<TestObject> anotherProperty = new SimpleObjectProperty<>(new TestObject("abc"));

        assertThat(anotherProperty.hashCode(), is(equalTo(property.hashCode())));
    }

    @Test
    public void shouldNotEqualPropertyWithAnotherValue() throws Exception {
        Property<TestObject> anotherProperty = new SimpleObjectProperty<>(new TestObject("xyz"));

        assertThat(anotherProperty, is(not(equalTo(property))));
        assertThat(property, is(not(equalTo(anotherProperty))));
    }

    @Test
    public void shouldNotProvideSameHashCodeAsPropertyWithAnotherValue() throws Exception {
        Property<TestObject> anotherProperty = new SimpleObjectProperty<>(new TestObject("xyz"));

        assertThat(anotherProperty.hashCode(), is(not(equalTo(property.hashCode()))));
    }

    @Test
    public void shouldProvideStringRepresentation() throws Exception {
        assertThat(property.toString(), is(equalTo("<TestObject{text='abc'}>")));
    }

    @Test
    public void shouldNotChangeValueOfBoundPropertyWhileBind() throws Exception {
        String originalValue = "bound property";
        TestObject testObject = new TestObject(originalValue);
        SimpleObjectProperty<TestObject> boundProperty = new SimpleObjectProperty<>(testObject);
        TestChangeListener<TestObject> boundPropertyListener = new TestChangeListener<>();
        boundProperty.addListener(boundPropertyListener);

        property.bind(boundProperty);

        assertThat(boundProperty.get(), is(equalTo(testObject)));
        assertThat(boundPropertyListener.calls, is(equalTo(0)));
    }

    @Test
    public void shouldChangeValueOfBoundPropertyWhenChangeOriginalProperty() throws Exception {
        String originalValue = "bound property";
        TestObject testObject = new TestObject(originalValue);
        SimpleObjectProperty<TestObject> boundProperty = new SimpleObjectProperty<>(testObject);
        TestChangeListener<TestObject> boundPropertyListener = new TestChangeListener<>();
        boundProperty.addListener(boundPropertyListener);

        property.bind(boundProperty);
        TestObject changedProperty = new TestObject("changed property");
        property.set(changedProperty);

        assertThat(boundProperty.get(), is(equalTo(changedProperty)));
        assertThat(boundPropertyListener.calls, is(equalTo(1)));
        assertThat(boundPropertyListener.newValue, is(equalTo(changedProperty)));
        assertThat(boundPropertyListener.oldValue, is(equalTo(testObject)));
        assertThat(boundPropertyListener.property, is(sameInstance(boundProperty)));
    }

    @Test
    public void shouldNotChangeValueOfUnboundPropertyWhenChangeOriginalProperty() throws Exception {
        String originalValue = "bound property";
        TestObject testObject = new TestObject(originalValue);
        SimpleObjectProperty<TestObject> boundProperty = new SimpleObjectProperty<>(testObject);
        TestChangeListener<TestObject> boundPropertyListener = new TestChangeListener<>();
        boundProperty.addListener(boundPropertyListener);

        property.bind(boundProperty);
        property.unbind(boundProperty);

        TestObject changedProperty = new TestObject("changed property");
        property.set(changedProperty);

        assertThat(boundProperty.get(), is(equalTo(testObject)));
        assertThat(boundPropertyListener.calls, is(equalTo(0)));
    }

    @Test
    public void shouldChangeValueOfBoundPropertiesWhenChangeOriginalProperty() throws Exception {
        TestObject originalTestObjectOne = new TestObject("bound property 1");
        SimpleObjectProperty<TestObject> boundPropertyOne = new SimpleObjectProperty<>(originalTestObjectOne);
        TestObject originalTestObjectTwo = new TestObject("bound property 2");
        SimpleObjectProperty<TestObject> boundPropertyTwo = new SimpleObjectProperty<>(originalTestObjectTwo);
        TestChangeListener<TestObject> boundPropertyListenerOne = new TestChangeListener<>();
        TestChangeListener<TestObject> boundPropertyListenerTwo = new TestChangeListener<>();

        boundPropertyOne.addListener(boundPropertyListenerOne);
        boundPropertyTwo.addListener(boundPropertyListenerTwo);

        property.bind(boundPropertyOne);
        property.bind(boundPropertyTwo);
        TestObject changedProperty = new TestObject("changed property");
        property.set(changedProperty);

        assertThat(boundPropertyOne.get(), is(equalTo(changedProperty)));
        assertThat(boundPropertyListenerOne.calls, is(equalTo(1)));
        assertThat(boundPropertyListenerOne.newValue, is(equalTo(changedProperty)));
        assertThat(boundPropertyListenerOne.oldValue, is(equalTo(originalTestObjectOne)));
        assertThat(boundPropertyListenerOne.property, is(sameInstance(boundPropertyOne)));

        assertThat(boundPropertyTwo.get(), is(equalTo(changedProperty)));
        assertThat(boundPropertyListenerTwo.calls, is(equalTo(1)));
        assertThat(boundPropertyListenerTwo.newValue, is(equalTo(changedProperty)));
        assertThat(boundPropertyListenerTwo.oldValue, is(equalTo(originalTestObjectTwo)));
        assertThat(boundPropertyListenerTwo.property, is(sameInstance(boundPropertyTwo)));
    }


    @Test
    public void shouldNotChangeValueOfUnboundPropertiesWhenChangeOriginalProperty() throws Exception {
        TestObject originalTestObjectOne = new TestObject("bound property 1");
        SimpleObjectProperty<TestObject> boundPropertyOne = new SimpleObjectProperty<>(originalTestObjectOne);
        TestObject originalTestObjectTwo = new TestObject("bound property 2");
        SimpleObjectProperty<TestObject> boundPropertyTwo = new SimpleObjectProperty<>(originalTestObjectTwo);
        TestChangeListener<TestObject> boundPropertyListenerOne = new TestChangeListener<>();
        TestChangeListener<TestObject> boundPropertyListenerTwo = new TestChangeListener<>();

        boundPropertyOne.addListener(boundPropertyListenerOne);
        boundPropertyTwo.addListener(boundPropertyListenerTwo);

        property.bind(boundPropertyOne);
        property.bind(boundPropertyTwo);
        TestObject changedProperty = new TestObject("changed property");
        property.unbind(boundPropertyOne);
        property.set(changedProperty);

        assertThat(boundPropertyOne.get(), is(equalTo(originalTestObjectOne)));
        assertThat(boundPropertyListenerOne.calls, is(equalTo(0)));

        assertThat(boundPropertyTwo.get(), is(equalTo(changedProperty)));
        assertThat(boundPropertyListenerTwo.calls, is(equalTo(1)));
        assertThat(boundPropertyListenerTwo.newValue, is(equalTo(changedProperty)));
        assertThat(boundPropertyListenerTwo.oldValue, is(equalTo(originalTestObjectTwo)));
        assertThat(boundPropertyListenerTwo.property, is(sameInstance(boundPropertyTwo)));
    }
}
