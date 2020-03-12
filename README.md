# Java Observable Properties

This project contains properties which are able to notify registered listeners about changes.

## Simple object properties

Simple properties are forced to contain a value. For optional values consider to use an `OptionalProperty` instance.

### Generic object property

#### Instance creation

```
Property<Object> property = new SimpleObjectProperty<>(new Object());
```

#### Retrieve the value

```
Object object = property.get();
```

#### Update the value

```
property.set(new Object());
```

#### Register a listener

##### "Classical style"

```
public class MyChangeListener implements ChangeListener<Object> {
    @Override
    public void onChange(ReadonlyProperty<Object> property, Object oldValue, Object newValue) {
        // property: the property instance which is currently notifying this listener
        // oldValue: the value before this change occurred
        // newValue: the new value
    }
}

ChangeListener<Object> listener = new MyChangeListener();
property.addListener(listener);
```

##### Anonymous class

```
property.addListener(new ChangeListener<TestObject>() {
    @Override
    public void onChange(ReadonlyProperty<TestObject> property, TestObject oldValue, TestObject newValue) {
        // property: the property instance which is currently notifying this listener
        // oldValue: the value before this change occurred
        // newValue: the new value
    }
});
```

##### Lambda style

```
property.addListener((property, oldValue, newValue) -> {
    // property: the property instance which is currently notifying this listener
    // oldValue: the value before this change occurred
    // newValue: the new value
});
```

#### Unregister a listener

```
property.removeListener(listener);
```

#### Bind a Property

```
property.bind(anotherProperty);
```

#### Unbind a Property

```
property.unbind(boundProperty);
```

### Typed properties

#### String

```
Property<String> property = new StringProperty<>("your property value");
```

#### Integer

```
Property<Integer> property = new IntegerProperty<>(123);
```

#### Double

```
Property<Double> property = new DoubleProperty<>(1.23);
```

#### Boolean

```
Property<Boolean> property = new BooleanProperty<>(true);
```

#### Long

```
Property<Long> property = new LongProperty<>(123L);
```

#### Float

```
Property<Float> property = new FloatProperty<>(1.23f);
```

#### Short

```
short myShort = 123;
Property<Short> property = new ShortProperty<>(myShort);
```

## Optional properties

Optional properties are able to be empty. Technically they contain a `null` value.

### Generic optional property

#### Instance creation

```
OptionalProperty<Object> property = new SimpleOptionalProperty<>(new Object()); // this property is filled
OptionalProperty<Object> empty = new SimpleOptionalProperty<>(); // this one is empty
```

#### Retrieve the optional value

```
Optional<Object> maybeObject = property.asOptional();
```

### Typed optional properties

#### String

```
OptionalProperty<String> property = new OptionalStringProperty<>("your property value");
OptionalProperty<String> empty = new OptionalStringProperty<>();
```

#### Integer

```
OptionalProperty<Integer> property = new OptionalIntegerProperty<>(123);
OptionalProperty<Integer> empty = new OptionalIntegerProperty<>();
```

#### Double

```
OptionalProperty<Double> property = new OptionalDoubleProperty<>(1.23);
OptionalProperty<Double> empty = new OptionalDoubleProperty<>();
```

#### Boolean

```
OptionalProperty<Boolean> property = new OptionalBooleanProperty<>(true);
OptionalProperty<Boolean> empty = new OptionalBooleanProperty<>();
```

#### Long

```
OptionalProperty<Long> property = new OptionalLongProperty<>(123L);
OptionalProperty<Long> empty = new OptionalLongProperty<>();
```

#### Float

```
OptionalProperty<Float> property = new OptionalFloatProperty<>(1.23f);
OptionalProperty<Float> empty = new OptionalFloatProperty<>();
```

#### Short

```
short myShort = 123;
OptionalProperty<Short> property = new OptionalShortProperty<>(myShort);
OptionalProperty<Short> empty = new OptionalShortProperty<>();
```
