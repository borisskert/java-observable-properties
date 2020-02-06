package com.github.borisskert.observableproperties;

import java.util.Objects;
import java.util.Optional;

/**
 * Implements an optional {@link Property} which may contain a {@code null} value.
 *
 * @param <T> the value type
 */
public class SimpleOptionalProperty<T> implements OptionalProperty<T> {

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
     * Creates an empty {@link Property}
     */
    public SimpleOptionalProperty() {
    }

    /**
     * Creates a {@link Property} with a initial value which may be {@code null}.
     *
     * @param value the initial value wich may be {@code null}
     */
    public SimpleOptionalProperty(T value) {
        this.value = value;
    }

    /* *****************************************************************************************************************
     * Implementation of Property<T>
     **************************************************************************************************************** */

    @Override
    public void set(T value) {
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

    @Override
    public Optional<T> asOptional() {
        return Optional.ofNullable(value);
    }

    /* *****************************************************************************************************************
     * Overrides of Object
     **************************************************************************************************************** */

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null) return false;

        if (getClass() == o.getClass()) {
            SimpleOptionalProperty<?> that = (SimpleOptionalProperty<?>) o;
            return Objects.equals(value, that.value);
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
