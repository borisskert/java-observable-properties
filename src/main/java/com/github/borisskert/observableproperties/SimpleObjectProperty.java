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
}
