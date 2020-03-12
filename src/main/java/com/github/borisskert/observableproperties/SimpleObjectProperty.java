package com.github.borisskert.observableproperties;

import java.util.Objects;

/**
 * Implements a generic property type which contains any type of value.
 * Attention: this property cannot be null. For a optional value consider to use {@link SimpleOptionalProperty}.
 *
 * @param <T> the value type.
 */
public class SimpleObjectProperty<T> implements Property<T> {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final ChangeListeners<T> listeners = new ChangeListeners<>();

    /* *****************************************************************************************************************
     * Mutable fields
     **************************************************************************************************************** */

    private T value;

    private BoundProperties<T> boundProperties = new BoundProperties<>();

    /* *****************************************************************************************************************
     * Constructor(s)
     **************************************************************************************************************** */

    /**
     * Creates an instance of a {@link SimpleObjectProperty}
     *
     * @param value the initial value which cannot be null
     */
    public SimpleObjectProperty(T value) {
        Objects.requireNonNull(value, "Parameter 'value' must not be null");

        this.value = value;
    }

    /* *****************************************************************************************************************
     * Implementation of Property<T>
     **************************************************************************************************************** */

    /**
     * Changes the value of this {@link Property} which cannot be set to {@code null}.
     * Consider to use {@link SimpleOptionalProperty} type for optional values.
     *
     * @param value the new value which cannot be {@code null}.
     */
    @Override
    public void set(T value) {
        Objects.requireNonNull(value, "Parameter 'value' must not be null");

        if (!Objects.equals(this.value, value)) {
            T oldValue = this.value;
            this.value = value;

            this.listeners.onChange(this, oldValue, value);

            this.boundProperties.setValue(value);
        }
    }

    @Override
    public T get() {
        return value;
    }

    @Override
    public void addListener(ChangeListener<T> listener) {
        listeners.addListener(listener);
    }

    @Override
    public void removeListener(ChangeListener<T> listener) {
        listeners.removeListener(listener);
    }

    @Override
    public void bind(Property<T> property) {
        this.boundProperties.bind(property);
    }

    @Override
    public void unbind(Property<T> boundProperty) {
        this.boundProperties.unbind(boundProperty);
    }

    /* *****************************************************************************************************************
     * Overrides of Object
     **************************************************************************************************************** */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        if (getClass() == o.getClass()) {
            SimpleObjectProperty<?> that = (SimpleObjectProperty<?>) o;
            return value.equals(that.value);
        }

        if (o instanceof Property) {
            Property<?> that = (Property<?>) o;
            return value.equals(that.get());
        }

        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(value);
    }

    @Override
    public String toString() {
        return "<" + value + '>';
    }
}
