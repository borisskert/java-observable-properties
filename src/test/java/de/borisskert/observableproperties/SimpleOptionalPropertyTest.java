package de.borisskert.observableproperties;

import org.hamcrest.core.Is;
import org.hamcrest.core.IsEqual;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import static org.junit.jupiter.api.Assertions.fail;

class SimpleOptionalPropertyTest {

    private OptionalProperty<TestObject> emptyProperty;
    private OptionalProperty<TestObject> property;

    private TestObject abcObject;
    private TestObject xyzObject;

    @BeforeEach
    public void setup() throws Exception {
        emptyProperty = new SimpleOptionalProperty<>();

        abcObject = new TestObject("abc");
        property = new SimpleOptionalProperty<>(abcObject);

        xyzObject = new TestObject("xyz");
    }

    @Test
    public void shouldProvideNullValueIfEmpty() throws Exception {
        assertThat(emptyProperty.get(), is(nullValue()));
    }

    @Test
    public void shouldProvideEmptyOptional() throws Exception {
        assertThat(emptyProperty.asOptional().isPresent(), is(false));
    }

    @Test
    public void shouldProvideValue() throws Exception {
        assertThat(property.get(), is(equalTo(abcObject)));
    }

    @Test
    public void shouldProvideValueAsOptional() throws Exception {
        Optional<TestObject> asOptional = property.asOptional();
        assertThat(asOptional.isPresent(), is(equalTo(true)));
        assertThat(asOptional.get(), is(equalTo(abcObject)));
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
    public void shouldProvideNullValueIfModifiedToNull() throws Exception {
        property.set(null);
        assertThat(property.get(), is(nullValue()));
    }

    @Test
    public void shouldProvideEmptyIfModifiedToNull() throws Exception {
        property.set(null);
        assertThat(property.asOptional().isPresent(), is(equalTo(false)));
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

    @Test
    public void shouldEqualPropertyWithSameValue() throws Exception {
        Property<TestObject> anotherProperty = new SimpleOptionalProperty<>(abcObject);

        assertThat(anotherProperty, is(equalTo(property)));
        assertThat(property, is(equalTo(anotherProperty)));
    }

    @Test
    public void shouldProvideSameHashCodeAsPropertyWithSameValue() throws Exception {
        Property<TestObject> anotherProperty = new SimpleOptionalProperty<>(abcObject);

        assertThat(anotherProperty.hashCode(), is(equalTo(property.hashCode())));
    }

    @Test
    public void shouldEqualEmptyProperty() throws Exception {
        Property<TestObject> anotherProperty = new SimpleOptionalProperty<>();

        assertThat(anotherProperty, is(equalTo(emptyProperty)));
        assertThat(emptyProperty, is(equalTo(anotherProperty)));
    }

    @Test
    public void shouldProvideSameHashCodeAsEmptyProperty() throws Exception {
        Property<TestObject> anotherProperty = new SimpleOptionalProperty<>();

        assertThat(anotherProperty.hashCode(), is(equalTo(emptyProperty.hashCode())));
    }

    @Test
    public void shouldEqualOptionalPropertyWithEqualValue() throws Exception {
        Property<TestObject> anotherProperty = new SimpleOptionalProperty<>(new TestObject("abc"));

        assertThat(anotherProperty, is(equalTo(property)));
        assertThat(property, is(equalTo(anotherProperty)));
    }

    @Test
    public void shouldProvideSameHashCodeAsNonOptionalPropertyWithEqualValue() throws Exception {
        Property<TestObject> anotherProperty = new SimpleObjectProperty<>(new TestObject("abc"));

        assertThat(anotherProperty.hashCode(), is(equalTo(property.hashCode())));
    }

    @Test
    public void shouldEqualNonOptionalPropertyWithEqualValue() throws Exception {
        Property<TestObject> anotherProperty = new SimpleObjectProperty<>(new TestObject("abc"));

        assertThat(property, is(equalTo(anotherProperty)));
    }

    @Test
    public void shouldProvideSameHashCodeAsPropertyWithEqualValue() throws Exception {
        Property<TestObject> anotherProperty = new SimpleOptionalProperty<>(new TestObject("abc"));

        assertThat(anotherProperty.hashCode(), is(equalTo(property.hashCode())));
    }

    @Test
    public void shouldNotEqualPropertyWithAnotherValue() throws Exception {
        Property<TestObject> anotherProperty = new SimpleOptionalProperty<>(new TestObject("xyz"));

        assertThat(anotherProperty, is(not(equalTo(property))));
        assertThat(property, is(not(equalTo(anotherProperty))));
    }

    @Test
    public void shouldNotProvideSameHashCodeAsPropertyWithAnotherValue() throws Exception {
        Property<TestObject> anotherProperty = new SimpleOptionalProperty<>(new TestObject("xyz"));

        assertThat(anotherProperty.hashCode(), is(not(equalTo(property.hashCode()))));
    }

    @Test
    public void shouldProvideStringRepresentation() throws Exception {
        assertThat(property.toString(), is(equalTo("<TestObject{text='abc'}>")));
    }

    @Test
    public void shouldProvideStringRepresentationForEmptyProperty() throws Exception {
        assertThat(emptyProperty.toString(), is(equalTo("<null>")));
    }

    @Test
    public void shouldThrowWhenTryToBindNonOptionalProperty() throws Exception {
        try {
            property.bind(new SimpleObjectProperty<>(new TestObject("not important")));
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertThat(e.getMessage(), Is.is(IsEqual.equalTo("You cannot bind a non-optional Property to an OptionalProperty")));
        }
    }

    @Test
    public void shouldNotChangeValueOfBoundPropertyWhileBind() throws Exception {
        String originalValue = "bound property";
        TestObject testObject = new TestObject(originalValue);
        SimpleOptionalProperty<TestObject> boundProperty = new SimpleOptionalProperty<>(testObject);
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
        SimpleOptionalProperty<TestObject> boundProperty = new SimpleOptionalProperty<>(testObject);
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
    public void shouldChangeValueOfBoundPropertyWhenChangePropertyToNull() throws Exception {
        String originalValue = "bound property";
        TestObject testObject = new TestObject(originalValue);
        SimpleOptionalProperty<TestObject> boundProperty = new SimpleOptionalProperty<>(testObject);
        TestChangeListener<TestObject> boundPropertyListener = new TestChangeListener<>();
        boundProperty.addListener(boundPropertyListener);

        property.bind(boundProperty);
        property.set(null);

        assertThat(boundProperty.get(), is(nullValue()));
        assertThat(boundPropertyListener.calls, is(equalTo(1)));
        assertThat(boundPropertyListener.newValue, is(nullValue()));
        assertThat(boundPropertyListener.oldValue, is(equalTo(testObject)));
        assertThat(boundPropertyListener.property, is(sameInstance(boundProperty)));
    }

    @Test
    public void shouldNotChangeValueOfUnboundPropertyWhenChangeProperty() throws Exception {
        String originalValue = "bound property";
        TestObject testObject = new TestObject(originalValue);
        SimpleOptionalProperty<TestObject> boundProperty = new SimpleOptionalProperty<>(testObject);
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
        SimpleOptionalProperty<TestObject> boundPropertyOne = new SimpleOptionalProperty<>(originalTestObjectOne);
        TestObject originalTestObjectTwo = new TestObject("bound property 2");
        SimpleOptionalProperty<TestObject> boundPropertyTwo = new SimpleOptionalProperty<>(originalTestObjectTwo);
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
        SimpleOptionalProperty<TestObject> boundPropertyOne = new SimpleOptionalProperty<>(originalTestObjectOne);
        TestObject originalTestObjectTwo = new TestObject("bound property 2");
        SimpleOptionalProperty<TestObject> boundPropertyTwo = new SimpleOptionalProperty<>(originalTestObjectTwo);
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

    @Test
    public void shouldChangeValueOfBoundPropertiesWhenChangePropertyToNull() throws Exception {
        TestObject originalTestObjectOne = new TestObject("bound property 1");
        SimpleOptionalProperty<TestObject> boundPropertyOne = new SimpleOptionalProperty<>(originalTestObjectOne);
        TestObject originalTestObjectTwo = new TestObject("bound property 2");
        SimpleOptionalProperty<TestObject> boundPropertyTwo = new SimpleOptionalProperty<>(originalTestObjectTwo);
        TestChangeListener<TestObject> boundPropertyListenerOne = new TestChangeListener<>();
        TestChangeListener<TestObject> boundPropertyListenerTwo = new TestChangeListener<>();

        boundPropertyOne.addListener(boundPropertyListenerOne);
        boundPropertyTwo.addListener(boundPropertyListenerTwo);

        property.bind(boundPropertyOne);
        property.bind(boundPropertyTwo);
        property.set(null);

        assertThat(boundPropertyOne.get(), is(nullValue()));
        assertThat(boundPropertyListenerOne.calls, is(equalTo(1)));
        assertThat(boundPropertyListenerOne.newValue, is(is(nullValue())));
        assertThat(boundPropertyListenerOne.oldValue, is(equalTo(originalTestObjectOne)));
        assertThat(boundPropertyListenerOne.property, is(sameInstance(boundPropertyOne)));

        assertThat(boundPropertyTwo.get(), is(is(nullValue())));
        assertThat(boundPropertyListenerTwo.calls, is(equalTo(1)));
        assertThat(boundPropertyListenerTwo.newValue, is(is(nullValue())));
        assertThat(boundPropertyListenerTwo.oldValue, is(equalTo(originalTestObjectTwo)));
        assertThat(boundPropertyListenerTwo.property, is(sameInstance(boundPropertyTwo)));
    }

    @Test
    public void shouldNotChangeValueOfUnboundPropertiesWhenChangePropertyToNull() throws Exception {
        TestObject originalTestObjectOne = new TestObject("bound property 1");
        SimpleOptionalProperty<TestObject> boundPropertyOne = new SimpleOptionalProperty<>(originalTestObjectOne);
        TestObject originalTestObjectTwo = new TestObject("bound property 2");
        SimpleOptionalProperty<TestObject> boundPropertyTwo = new SimpleOptionalProperty<>(originalTestObjectTwo);
        TestChangeListener<TestObject> boundPropertyListenerOne = new TestChangeListener<>();
        TestChangeListener<TestObject> boundPropertyListenerTwo = new TestChangeListener<>();

        boundPropertyOne.addListener(boundPropertyListenerOne);
        boundPropertyTwo.addListener(boundPropertyListenerTwo);

        property.bind(boundPropertyOne);
        property.bind(boundPropertyTwo);
        property.unbind(boundPropertyOne);
        property.set(null);

        assertThat(boundPropertyOne.get(), is(equalTo(originalTestObjectOne)));
        assertThat(boundPropertyListenerOne.calls, is(equalTo(0)));

        assertThat(boundPropertyTwo.get(), is(is(nullValue())));
        assertThat(boundPropertyListenerTwo.calls, is(equalTo(1)));
        assertThat(boundPropertyListenerTwo.newValue, is(is(nullValue())));
        assertThat(boundPropertyListenerTwo.oldValue, is(equalTo(originalTestObjectTwo)));
        assertThat(boundPropertyListenerTwo.property, is(sameInstance(boundPropertyTwo)));
    }
}
