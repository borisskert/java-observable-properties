package com.github.borisskert.observableproperties;

/**
 * Restricting interfact which represents an observable {@link Property} without providing any method to change the value.
 *
 * @param <T> the value type
 */
public interface ReadonlyProperty<T> {

    /**
     * Returns the current value hold of this {@link Property}
     *
     * @return the current value
     */
    T get();

    /**
     * Adds a {@link ChangeListener} to this {@link Property}
     *
     * @param listener to be add
     */
    void addListener(ChangeListener<T> listener);

    /**
     * Removes a {@link ChangeListener} to this {@link Property}
     *
     * @param listener to be removed
     */
    void removeListener(ChangeListener<T> listener);
}
