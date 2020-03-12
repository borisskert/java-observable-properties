package com.github.borisskert.observableproperties;

/**
 * Defines a {@link ChangeListener} which can observe a {@link Property}.
 *
 * @param <T> the property type
 */
public interface ChangeListener<T> {

    /**
     * Called on every change of the observed property
     *
     * @param property the observed property
     * @param oldValue the value before the change
     * @param newValue the value after the change
     */
    void onChange(ReadonlyProperty<T> property, T oldValue, T newValue);
}
