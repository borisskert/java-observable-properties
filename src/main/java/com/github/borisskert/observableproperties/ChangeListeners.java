package com.github.borisskert.observableproperties;

import java.util.LinkedList;
import java.util.List;

/**
 * Encapsulates holding, adding and removing and sending change notifications to {@link ChangeListener}s.
 * This class is package-private and should only used within this package.
 *
 * @param <T> the value type
 */
class ChangeListeners<T> implements ChangeListener<T> {

    /* *****************************************************************************************************************
     * Readonly fields
     **************************************************************************************************************** */

    private final List<ChangeListener<T>> listeners = new LinkedList<>();

    /* *****************************************************************************************************************
     * Implementation of ChangeListener<T> interface
     **************************************************************************************************************** */

    @Override
    public void onChange(ReadonlyProperty<T> property, T oldValue, T newValue) {
        this.listeners.forEach(listener -> listener.onChange(property, oldValue, newValue));
    }

    /* *****************************************************************************************************************
     * Public contract
     **************************************************************************************************************** */

    /**
     * Adds a {@link ChangeListener} to this instance.
     *
     * @param listener the {@link ChangeListener} to be added.
     */
    void addListener(ChangeListener<T> listener) {
        listeners.add(listener);
    }

    /**
     * Removes a {@link ChangeListener} from this instance.
     *
     * @param listener the {@link ChangeListener} to be removed.
     */
    void removeListener(ChangeListener<T> listener) {
        listeners.remove(listener);
    }
}
