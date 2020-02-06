package com.github.borisskert;

import java.util.Objects;

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

    public SimpleObjectProperty() {
        this.value = null;
    }

    public SimpleObjectProperty(T value) {
        this.value = value;
    }

    /* *****************************************************************************************************************
     * Implementation of Property<T>
     **************************************************************************************************************** */

    @Override
    public void set(T value) {
        if(! Objects.equals(this.value, value)) {
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
